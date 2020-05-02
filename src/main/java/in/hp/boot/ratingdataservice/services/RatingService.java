package in.hp.boot.ratingdataservice.services;

import in.hp.boot.ratingdataservice.dto.RatingDto;
import in.hp.boot.ratingdataservice.entities.CompositeKey;
import in.hp.boot.ratingdataservice.entities.Rating;
import in.hp.boot.ratingdataservice.exceptions.ExceptionUtils;
import in.hp.boot.ratingdataservice.models.RatingModel;
import in.hp.boot.ratingdataservice.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public RatingDto getRatingsForUser(String userId) {
        List<Rating> ratings = ratingRepository.findByCompositeKeyUserId(userId)
                .orElseThrow(ExceptionUtils.getResourceNotFoundExceptionSupplier(userId));
        List<RatingModel> ratingModel = ratings.stream()
                .map(rating -> new RatingModel(rating.getCompositeKey().getMovieId(), rating.getRating()))
                .collect(Collectors.toList());
        return new RatingDto(userId, ratingModel);
    }

    public RatingModel getRatingForMovie(String userId, String movieId) {
        Rating rating = ratingRepository.findById(new CompositeKey(userId, movieId))
                .orElseThrow(ExceptionUtils.getResourceNotFoundExceptionSupplier(userId, movieId));
        return new RatingModel(rating.getCompositeKey().getMovieId(), rating.getRating());
    }

    public void addRatingForUser(String userId, RatingModel ratingModel) {
        CompositeKey compositeKey = new CompositeKey(userId, ratingModel.getMovieId());
        ratingRepository.findById(compositeKey)
                .ifPresent(ExceptionUtils.getResourceConflictExceptionConsumer(userId, ratingModel.getMovieId()));
        ratingRepository.save(new Rating(compositeKey, ratingModel.getRating()));
    }

    public void updateRatingForUser(String userId, RatingModel ratingModel) {
        Rating rating = ratingRepository.findById(new CompositeKey(userId, ratingModel.getMovieId()))
                .orElseThrow(ExceptionUtils.getResourceNotFoundExceptionSupplier(userId, ratingModel.getMovieId()));
        rating.setRating(ratingModel.getRating());
        ratingRepository.save(rating);
    }

    @Transactional
    public void deleteAllRatingsForUser(String userId) {
        List<Rating> ratings = ratingRepository.findByCompositeKeyUserId(userId)
                .orElseThrow(ExceptionUtils.getResourceNotFoundExceptionSupplier(userId));
        ratingRepository.deleteAll(ratings);
    }

    public void deleteRatingForUser(String userId, String movieId) {
        Rating rating = ratingRepository.findById(new CompositeKey(userId, movieId))
                .orElseThrow(ExceptionUtils.getResourceNotFoundExceptionSupplier(userId, movieId));
        ratingRepository.delete(rating);
    }
}

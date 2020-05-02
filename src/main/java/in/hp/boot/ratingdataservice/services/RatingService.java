package in.hp.boot.ratingdataservice.services;

import in.hp.boot.ratingdataservice.dto.RatingDto;
import in.hp.boot.ratingdataservice.entities.CompositeKey;
import in.hp.boot.ratingdataservice.entities.Rating;
import in.hp.boot.ratingdataservice.models.RatingModel;
import in.hp.boot.ratingdataservice.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public RatingDto getRatingsForUser(String userId) {
        List<Rating> ratings = ratingRepository.findByCompositeKeyUserId(userId);
        List<RatingModel> ratingModel = ratings.stream()
                .map(rating -> new RatingModel(rating.getCompositeKey().getMovieId(), rating.getRating()))
                .collect(Collectors.toList());
        return new RatingDto(userId, ratingModel);
    }

    public void addRatingForUser(String userId, RatingModel ratingModel) {
        CompositeKey compositeKey = new CompositeKey(userId, ratingModel.getMovieId());
        ratingRepository.save(new Rating(compositeKey, ratingModel.getRating()));
    }

    public RatingModel getRatingForMovie(String userId, String movieId) {
        Optional<Rating> optionalRating = ratingRepository.findById(new CompositeKey(userId, movieId));
        if (optionalRating.isPresent()) {
            Rating rating = optionalRating.get();
            return new RatingModel(rating.getCompositeKey().getMovieId(), rating.getRating());
        }
        return null;
    }

    public void updateRatingForUser(String userId, RatingModel ratingModel) {
        CompositeKey compositeKey = new CompositeKey(userId, ratingModel.getMovieId());
        Optional<Rating> optionalRating = ratingRepository.findById(compositeKey);
        optionalRating.ifPresent(rating -> {
            rating.setRating(ratingModel.getRating());
            ratingRepository.save(rating);
        });
    }

    @Transactional
    public void deleteAllRatingsForUser(String userId) {
        ratingRepository.deleteByCompositeKeyUserId(userId);
    }

    public void deleteRatingForUser(String userId, String movieId) {
        ratingRepository.deleteById(new CompositeKey(userId, movieId));
    }
}

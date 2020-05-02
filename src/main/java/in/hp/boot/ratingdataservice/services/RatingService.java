package in.hp.boot.ratingdataservice.services;

import in.hp.boot.ratingdataservice.dto.RatingDto;
import in.hp.boot.ratingdataservice.entities.CompositeKey;
import in.hp.boot.ratingdataservice.entities.Rating;
import in.hp.boot.ratingdataservice.models.RatingModel;
import in.hp.boot.ratingdataservice.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}

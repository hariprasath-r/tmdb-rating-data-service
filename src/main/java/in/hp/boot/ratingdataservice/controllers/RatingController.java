package in.hp.boot.ratingdataservice.controllers;

import in.hp.boot.ratingdataservice.dto.RatingDto;
import in.hp.boot.ratingdataservice.models.RatingModel;
import in.hp.boot.ratingdataservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{userId}")
    public RatingDto getRatingsForUser(@PathVariable String userId) {
        return ratingService.getRatingsForUser(userId);
    }

    @GetMapping("/{userId}/{movieId}")
    public void getRatingForMovie(@PathVariable String userId, @PathVariable String movieId) {

    }

    @PostMapping("/{userId}")
    public void setRatingForUser(@PathVariable String userId, @RequestBody RatingModel ratingModel) {
        ratingService.addRatingForUser(userId, ratingModel);
    }

    @PutMapping("/{userId}/{movieId}")
    public void updateRatingForMovie(@PathVariable String userId, @PathVariable String movieId) {

    }

    @DeleteMapping("/{userId}")
    public void deleteRatingsForUser(@PathVariable String userId) {

    }

    @DeleteMapping("/{userId}/{movieId}")
    public void deleteRatingForMovie(@PathVariable String userId, @PathVariable String movieId) {

    }
}

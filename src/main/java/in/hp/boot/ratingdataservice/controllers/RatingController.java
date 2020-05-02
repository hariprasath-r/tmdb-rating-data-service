package in.hp.boot.ratingdataservice.controllers;

import in.hp.boot.ratingdataservice.dto.RatingDto;
import in.hp.boot.ratingdataservice.models.RatingModel;
import in.hp.boot.ratingdataservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{userId}")
    public ResponseEntity<RatingDto> getRatingsForUser(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingsForUser(userId));
    }

    @GetMapping("/{userId}/{movieId}")
    public ResponseEntity<RatingModel> getRatingForMovie(@PathVariable String userId, @PathVariable String movieId) {
        return ResponseEntity.ok(ratingService.getRatingForMovie(userId, movieId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Object> setRatingForUser(@PathVariable String userId, @RequestBody RatingModel ratingModel) {
        ratingService.addRatingForUser(userId, ratingModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateRatingForMovie(@PathVariable String userId, @RequestBody RatingModel ratingModel) {
        ratingService.updateRatingForUser(userId, ratingModel);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteAllRatingsForUser(@PathVariable String userId) {
        ratingService.deleteAllRatingsForUser(userId);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{userId}/{movieId}")
    public ResponseEntity<Object> deleteRatingForMovie(@PathVariable String userId, @PathVariable String movieId) {
        ratingService.deleteRatingForUser(userId, movieId);
        return ResponseEntity.accepted().build();
    }
}

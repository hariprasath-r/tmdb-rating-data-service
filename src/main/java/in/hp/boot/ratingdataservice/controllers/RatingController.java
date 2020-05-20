package in.hp.boot.ratingdataservice.controllers;

import in.hp.boot.ratingdataservice.dto.RatingDto;
import in.hp.boot.ratingdataservice.models.RatingModel;
import in.hp.boot.ratingdataservice.services.RatingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/v1/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @ApiOperation(value = "Getting all movies rated by user.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved."),
            @ApiResponse(code = 404, message = "User not found.")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<RatingDto> getRatingsForUser(@PathVariable String userId) {
        log.info("getRatingsForUser");
        return ResponseEntity.ok(ratingService.getRatingsForUser(userId));
    }

    @ApiOperation(value = "Getting a movie rated by user.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved."),
            @ApiResponse(code = 404, message = "User or movie not found.")
    })
    @GetMapping("/{userId}/{movieId}")
    public ResponseEntity<RatingModel> getRatingForMovie(@PathVariable String userId,
                                                         @PathVariable String movieId) {
        log.info("getRatingForMovie");
        return ResponseEntity.ok(ratingService.getRatingForMovie(userId, movieId));
    }

    @ApiOperation(value = "Adding a movie rating to a user.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added."),
            @ApiResponse(code = 409, message = "User has already rated this movie, Try updating.")
    })
    @PostMapping("/{userId}")
    public ResponseEntity<Object> setRatingForUser(@PathVariable String userId,
                                                   @Valid @RequestBody RatingModel ratingModel) {
        log.info("setRatingForUser");
        ratingService.addRatingForUser(userId, ratingModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Updating a movie rating of a user.")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Successfully updated."),
            @ApiResponse(code = 409, message = "User or movie not found.")
    })
    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateRatingForMovie(@PathVariable String userId
            , @Valid @RequestBody RatingModel ratingModel) {
        log.info("updateRatingForMovie");
        ratingService.updateRatingForUser(userId, ratingModel);
        return ResponseEntity.accepted().build();
    }

    @ApiOperation(value = "Deleting all movie ratings of a user.")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Will be deleted shortly."),
            @ApiResponse(code = 409, message = "User not found.")
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteAllRatingsForUser(@PathVariable String userId) {
        log.info("deleteAllRatingsForUser");
        ratingService.deleteAllRatingsForUser(userId);
        return ResponseEntity.accepted().build();
    }

    @ApiOperation(value = "Deleting a movie rating of a user.")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Will be deleted shortly."),
            @ApiResponse(code = 409, message = "User or movie not found.")
    })
    @DeleteMapping("/{userId}/{movieId}")
    public ResponseEntity<Object> deleteRatingForMovie(@PathVariable String userId,
                                                       @PathVariable String movieId) {
        log.info("deleteRatingForMovie");
        ratingService.deleteRatingForUser(userId, movieId);
        return ResponseEntity.accepted().build();
    }
}

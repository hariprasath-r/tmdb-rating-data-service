package in.hp.boot.ratingdataservice.controllers;

import in.hp.boot.ratingdataservice.dto.WatchlistDto;
import in.hp.boot.ratingdataservice.services.WatchlistService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @ApiOperation(value = "Adding a movie to user watchlist")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added."),
            @ApiResponse(code = 409, message = "User Already has movie in his list.")
    })
    @PostMapping("/{userId}/{movieId}")
    public ResponseEntity<Object> addMovieToWatchlistForUser(@PathVariable String userId, @PathVariable String movieId) {
        watchlistService.addMovieToUserWatchlist(userId, movieId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "Getting all movies for a user.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved.", response = WatchlistDto.class),
            @ApiResponse(code = 404, message = "User not found.")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<WatchlistDto> getWatchlistForUser(@PathVariable String userId) {
        return ResponseEntity.ok(watchlistService.getWatchlistForUser(userId));
    }

    @ApiOperation(value = "Deleting all movies for a user.")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Will be deleted shortly."),
            @ApiResponse(code = 404, message = "User not found.")
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteAllMoviesForUser(@PathVariable String userId) {
        watchlistService.deleteAllMoviesForUser(userId);
        return ResponseEntity.accepted().build();
    }

    @ApiOperation(value = "Deleting a movie for a user.")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Will be deleted shortly."),
            @ApiResponse(code = 404, message = "User or movie is not found.")
    })
    @DeleteMapping("/{userId}/{movieId}")
    public ResponseEntity<Object> deleteMovieFromUser(@PathVariable String userId, @PathVariable String movieId) {
        watchlistService.deleteMovieFromUser(userId, movieId);
        return ResponseEntity.accepted().build();
    }
}

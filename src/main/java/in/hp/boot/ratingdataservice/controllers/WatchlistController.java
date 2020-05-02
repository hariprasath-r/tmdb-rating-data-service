package in.hp.boot.ratingdataservice.controllers;

import in.hp.boot.ratingdataservice.dto.WatchlistDto;
import in.hp.boot.ratingdataservice.services.WatchlistService;
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

    @PostMapping("/{userId}/{movieId}")
    public ResponseEntity<Object> addMovieToWatchlistForUser(@PathVariable String userId, @PathVariable String movieId) {
        watchlistService.addMovieToUserWatchlist(userId, movieId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand()
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{userId}")
    public WatchlistDto getWatchlistForUser(@PathVariable String userId) {
        return watchlistService.getWatchlistForUser(userId);
    }

    @DeleteMapping("/{userId}/{movieId}")
    public ResponseEntity<Object> deleteMovieFromUser(@PathVariable String userId, @PathVariable String movieId) {
        watchlistService.deleteMovieFromUser(userId, movieId);
        return ResponseEntity.accepted().build();
    }
}

package in.hp.boot.ratingdataservice.controllers;

import in.hp.boot.ratingdataservice.dto.WatchlistDto;
import in.hp.boot.ratingdataservice.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @PostMapping("/{userId}/{movieId}")
    public void addMovieToWatchlistForUser(@PathVariable String userId, @PathVariable String movieId) {
        watchlistService.addMovieToUserWatchlist(userId, movieId);
    }

    @GetMapping("/{userId}")
    public WatchlistDto getWatchlistForUser(@PathVariable String userId) {
        return watchlistService.getWatchlistForUser(userId);
    }

    @DeleteMapping("/{userId}/{movieId}")
    public void deleteMovieFromUser(@PathVariable String userId, @PathVariable String movieId) {
        watchlistService.deleteMovieFromUser(userId, movieId);
    }
}

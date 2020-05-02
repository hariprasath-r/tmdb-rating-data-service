package in.hp.boot.ratingdataservice.controllers;

import in.hp.boot.ratingdataservice.entities.Watchlist;
import in.hp.boot.ratingdataservice.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/wishlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @PostMapping("/{userId}/{movieId}")
    public void addMovieToWatchlistForUser(@PathVariable String userId, @PathVariable String movieId) {
        watchlistService.addMovieToUserWatchlist(userId, movieId);
    }

    @GetMapping("/{userId}")
    public List<Watchlist> getWatchlistForUser(@PathVariable String userId) {
        return watchlistService.getWatchlistForUser(userId);
    }
}

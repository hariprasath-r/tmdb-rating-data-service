package in.hp.boot.ratingdataservice.services;

import in.hp.boot.ratingdataservice.entities.CompositeKey;
import in.hp.boot.ratingdataservice.entities.Watchlist;
import in.hp.boot.ratingdataservice.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    public void addMovieToUserWatchlist(String userId, String movieId) {
        CompositeKey compositeKey = new CompositeKey(userId, movieId);
        Watchlist watchlist = new Watchlist();
        watchlist.setCompositeKey(compositeKey);
        watchlistRepository.save(watchlist);
    }

    public List<Watchlist> getWatchlistForUser(String userId) {
        return watchlistRepository.findByCompositeKeyUserId(userId);
    }
}

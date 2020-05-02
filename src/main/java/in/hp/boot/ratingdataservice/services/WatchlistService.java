package in.hp.boot.ratingdataservice.services;

import in.hp.boot.ratingdataservice.dto.WatchlistDto;
import in.hp.boot.ratingdataservice.entities.CompositeKey;
import in.hp.boot.ratingdataservice.entities.Watchlist;
import in.hp.boot.ratingdataservice.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public WatchlistDto getWatchlistForUser(String userId) {
        List<Watchlist> watchlists = watchlistRepository.findByCompositeKeyUserId(userId);
        List<String> movies = watchlists.stream()
                .map(watchlist -> watchlist.getCompositeKey().getMovieId())
                .collect(Collectors.toList());
        return new WatchlistDto(userId, movies);
    }

    public void deleteMovieFromUser(String userId, String movieId) {
        watchlistRepository.deleteById(new CompositeKey(userId, movieId));
    }
}

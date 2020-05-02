package in.hp.boot.ratingdataservice.services;

import in.hp.boot.ratingdataservice.dto.WatchlistDto;
import in.hp.boot.ratingdataservice.entities.CompositeKey;
import in.hp.boot.ratingdataservice.entities.Watchlist;
import in.hp.boot.ratingdataservice.exceptions.ExceptionUtils;
import in.hp.boot.ratingdataservice.exceptions.ResourceNotFoundException;
import in.hp.boot.ratingdataservice.repositories.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    public void addMovieToUserWatchlist(String userId, String movieId) {
        CompositeKey compositeKey = new CompositeKey(userId, movieId);
        watchlistRepository.findById(compositeKey)
                .ifPresent(ExceptionUtils.getResourceConflictExceptionConsumer(userId, movieId));
        watchlistRepository.save(new Watchlist(compositeKey));
    }


    public WatchlistDto getWatchlistForUser(String userId) {
        List<Watchlist> watchlists = watchlistRepository.findByCompositeKeyUserId(userId)
                .<RuntimeException>orElseThrow(ExceptionUtils.getResourceNotFoundExceptionSupplier(userId));
        List<String> movies = watchlists.stream()
                .map(watchlist -> watchlist.getCompositeKey().getMovieId())
                .collect(Collectors.toList());
        return new WatchlistDto(userId, movies);
    }

    public void deleteMovieFromUser(String userId, String movieId) {
        try {
            watchlistRepository.deleteById(new CompositeKey(userId, movieId));
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(ExceptionUtils.formExceptionMessageFromUserIdMovieId(userId, movieId));
        }
    }

    public void deleteAllMoviesForUser(String userId) {
        List<Watchlist> watchlists = watchlistRepository.findByCompositeKeyUserId(userId)
                .<RuntimeException>orElseThrow(ExceptionUtils.getResourceNotFoundExceptionSupplier(userId));
        watchlistRepository.deleteAll(watchlists);
    }
}

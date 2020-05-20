package in.hp.boot.ratingdataservice.services;

import in.hp.boot.ratingdataservice.dto.WatchlistDto;
import in.hp.boot.ratingdataservice.entities.CompositeKey;
import in.hp.boot.ratingdataservice.entities.Watchlist;
import in.hp.boot.ratingdataservice.exceptions.ExceptionUtils;
import in.hp.boot.ratingdataservice.exceptions.ResourceNotFoundException;
import in.hp.boot.ratingdataservice.repositories.WatchlistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    public void addMovieToUserWatchlist(String userId, String movieId) {
        log.info("addMovieToUserWatchlist userId [{}], movieId [{}]", userId, movieId);
        CompositeKey compositeKey = new CompositeKey(userId, movieId);
        watchlistRepository.findById(compositeKey)
                .ifPresent(ExceptionUtils.getResourceConflictExceptionConsumer(userId, movieId));
        watchlistRepository.save(new Watchlist(compositeKey));
        log.info("addMovieToUserWatchlist done userId [{}], movieId [{}]", userId, movieId);
    }


    public WatchlistDto getWatchlistForUser(String userId) {
        log.info("getWatchlistForUser userId [{}]", userId);
        List<Watchlist> watchlists = watchlistRepository.findByCompositeKeyUserId(userId)
                .<RuntimeException>orElseThrow(ExceptionUtils.getResourceNotFoundExceptionSupplier(userId));
        List<String> movies = watchlists.stream()
                .map(watchlist -> watchlist.getCompositeKey().getMovieId())
                .collect(Collectors.toList());
        log.info("getWatchlistForUser done userId [{}], watchLists [{}], movies [{}]", userId, watchlists, movies);
        return new WatchlistDto(userId, movies);
    }

    public void deleteMovieFromUser(String userId, String movieId) {
        log.info("deleteMovieFromUser userId [{}], movieId [{}]", userId, movieId);
        try {
            watchlistRepository.deleteById(new CompositeKey(userId, movieId));
        } catch (EmptyResultDataAccessException e) {
            log.error("Exception deleteMovieFromUser [{}]", e.getMessage());
            throw new ResourceNotFoundException(ExceptionUtils.formExceptionMessageFromUserIdMovieId(userId, movieId));
        }
        log.info("deleteMovieFromUser done userId [{}], movieId [{}]", userId, movieId);
    }

    public void deleteAllMoviesForUser(String userId) {
        log.info("deleteAllMoviesForUser userId [{}]", userId);
        List<Watchlist> watchlists = watchlistRepository.findByCompositeKeyUserId(userId)
                .<RuntimeException>orElseThrow(ExceptionUtils.getResourceNotFoundExceptionSupplier(userId));
        watchlistRepository.deleteAll(watchlists);
        log.info("deleteAllMoviesForUser done userId [{}]", userId);
    }
}

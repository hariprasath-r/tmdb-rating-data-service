package in.hp.boot.ratingdataservice.repositories;

import in.hp.boot.ratingdataservice.entities.CompositeKey;
import in.hp.boot.ratingdataservice.entities.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchlistRepository extends JpaRepository<Watchlist, CompositeKey> {
    List<Watchlist> findByCompositeKeyUserId(String userId);
}

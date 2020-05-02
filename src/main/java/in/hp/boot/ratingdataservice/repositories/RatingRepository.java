package in.hp.boot.ratingdataservice.repositories;

import in.hp.boot.ratingdataservice.entities.CompositeKey;
import in.hp.boot.ratingdataservice.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, CompositeKey> {
    Optional<List<Rating>> findByCompositeKeyUserId(String userId);

    void deleteByCompositeKeyUserId(String userId);
}

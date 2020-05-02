package in.hp.boot.ratingdataservice.repositories;

import in.hp.boot.ratingdataservice.entities.CompositeKey;
import in.hp.boot.ratingdataservice.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, CompositeKey> {
    List<Rating> findByCompositeKeyUserId(String userId);
}

package in.hp.boot.ratingdataservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@Embeddable
public class CompositeKey {
    private String userId;
    private String movieId;
}

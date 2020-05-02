package in.hp.boot.ratingdataservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CompositeKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String movieId;
}

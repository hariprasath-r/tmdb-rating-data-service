package in.hp.boot.ratingdataservice.entities;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Rating")
@Data
public class Rating {

    @EmbeddedId
    private CompositeKey compositeKey;
}

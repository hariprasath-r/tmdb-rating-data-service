package in.hp.boot.ratingdataservice.entities;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "Wishlist")
@Data
public class Wishlist {
    @EmbeddedId
    private CompositeKey compositeKey;

    @GeneratedValue
    private Integer id;
}

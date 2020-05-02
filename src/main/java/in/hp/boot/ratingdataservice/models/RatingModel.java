package in.hp.boot.ratingdataservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingModel {
    private String movieId;
    private Double rating;
}

package in.hp.boot.ratingdataservice.dto;

import in.hp.boot.ratingdataservice.models.RatingModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RatingDto {
    private String userId;
    private List<RatingModel> ratings;
}

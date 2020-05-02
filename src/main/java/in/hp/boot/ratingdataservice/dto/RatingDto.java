package in.hp.boot.ratingdataservice.dto;

import in.hp.boot.ratingdataservice.models.RatingModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@ApiModel("User Ratings")
public class RatingDto {

    private String userId;

    @ApiModelProperty(position = 1)
    private List<RatingModel> ratings;
}

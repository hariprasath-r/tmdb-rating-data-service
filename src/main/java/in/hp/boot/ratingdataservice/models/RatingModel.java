package in.hp.boot.ratingdataservice.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel("Rating")
public class RatingModel {

    @ApiModelProperty(position = 0)
    private String movieId;

    @ApiModelProperty(position = 1)
    private Double rating;
}

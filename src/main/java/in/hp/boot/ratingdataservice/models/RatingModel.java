package in.hp.boot.ratingdataservice.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@ApiModel("Rating")
public class RatingModel {

    @ApiModelProperty(position = 0, required = true)
    private String movieId;

    @Range(min = 1, max = 10, message = "Value must be between 1 and 10")
    @ApiModelProperty(position = 1, required = true, allowableValues = "1 to 10")
    private Double rating;
}

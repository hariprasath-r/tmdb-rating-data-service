package in.hp.boot.ratingdataservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@ApiModel("Watchlist")
public class WatchlistDto {

    @ApiModelProperty(position = 0)
    private String userId;

    @ApiModelProperty(name = "Movies", notes = "Movies in user watchlist")
    private List<String> movies;
}

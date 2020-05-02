package in.hp.boot.ratingdataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WatchlistDto {
    private String userId;
    private List<String> movieId;
}

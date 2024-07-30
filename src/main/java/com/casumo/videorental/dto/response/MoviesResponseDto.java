package com.casumo.videorental.dto.response;

import com.casumo.videorental.model.MoviesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Miguel Castro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoviesResponseDto {

    private Long id;

    private String title;

    private String type;
    
    public static MoviesResponseDto convertEntityForMoviesResponseDto(MoviesModel moviesModel) {
        return new MoviesResponseDto(moviesModel.getId(), moviesModel.getTitle(), moviesModel.getType().name());
    }
}

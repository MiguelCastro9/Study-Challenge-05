package com.casumo.videorental.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author Miguel Castro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalReceiptResponseDto {

    private List<RentedMovieDto> rentedMovies;
    
    private Double totalPrice;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RentedMovieDto {
        
        private Long id;
        
        private String title;
        
        private String type;
        
        private Integer rentalDays;
        
        private Double price;
    }
}

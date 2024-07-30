package com.casumo.videorental.dto.request;

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
public class RentalRequestDto {
    
    private Long movieId;
    
    private Integer totalDays;
}

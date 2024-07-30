package com.casumo.videorental.service;

import com.casumo.videorental.dto.request.RentalRequestDto;
import com.casumo.videorental.dto.response.MoviesResponseDto;
import com.casumo.videorental.dto.response.RentalReceiptResponseDto;
import com.casumo.videorental.model.MoviesModel;
import java.util.List;

/**
 *
 * @author Miguel Castro
 */
public interface MoviesService {
    
    public List<MoviesResponseDto> findAllMovies();
    
    public RentalReceiptResponseDto rentingMovies(List<RentalRequestDto> rentalRequestDto);
    
    public String returnMovies(List<RentalRequestDto> returnRequestDto);

    public Double calculateRentalPrice(MoviesModel movie, Integer rentalDays);
    
    public Double calculateLateRentalPrice(MoviesModel movie, Double totalExtra, Integer lateDays);
}

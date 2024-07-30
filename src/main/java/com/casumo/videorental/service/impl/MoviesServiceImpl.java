package com.casumo.videorental.service.impl;

import com.casumo.videorental.dto.request.RentalRequestDto;
import com.casumo.videorental.dto.response.MoviesResponseDto;
import com.casumo.videorental.dto.response.RentalReceiptResponseDto;
import com.casumo.videorental.exception.InvalidRentalDaysException;
import com.casumo.videorental.exception.MovieNotFoundException;
import com.casumo.videorental.model.MoviesModel;
import com.casumo.videorental.repository.MoviesRepository;
import com.casumo.videorental.service.MoviesService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miguel Castro
 */
@Service
public class MoviesServiceImpl implements MoviesService {

    @Value("${prices.premium}")
    private double premiumPrice;

    @Value("${prices.basic}")
    private double basicPrice;

    @Autowired
    MoviesRepository moviesRepository;

    @Override
    public List<MoviesResponseDto> findAllMovies() {
        return moviesRepository.findAll().stream()
                .map(MoviesResponseDto::convertEntityForMoviesResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public RentalReceiptResponseDto rentingMovies(List<RentalRequestDto> rentalRequestDto) {

        List<Long> movieIds = rentalRequestDto.stream()
                .map(RentalRequestDto::getMovieId)
                .collect(Collectors.toList());

        List<MoviesModel> moviesModels = moviesRepository.findAllById(movieIds);

        if (moviesModels.size() != rentalRequestDto.size()) {
            throw new MovieNotFoundException("One or more movies not found.");
        }

        List<Integer> rentalDays = rentalRequestDto.stream()
                .map(RentalRequestDto::getTotalDays)
                .collect(Collectors.toList());

        List<RentalReceiptResponseDto.RentedMovieDto> rentedMovies = new ArrayList<>();

        Double totalPrice = 0.0;

        for (int i = 0; i < moviesModels.size(); i++) {
            MoviesModel movie = moviesModels.get(i);
            Integer days = rentalDays.get(i);
            if (days <= 0) {
                throw new InvalidRentalDaysException("Rental days must be greater than zero.");
            }
            Double price = calculateRentalPrice(movie, days);
            totalPrice += price;
            rentedMovies.add(new RentalReceiptResponseDto.RentedMovieDto(movie.getId(), movie.getTitle(),
                    movie.getType().name(), days, price));
        }

        RentalReceiptResponseDto rentalReceiptResponseDto = new RentalReceiptResponseDto();
        rentalReceiptResponseDto.setRentedMovies(rentedMovies);
        rentalReceiptResponseDto.setTotalPrice(totalPrice);

        return rentalReceiptResponseDto;
    }

    @Override
    public String returnMovies(List<RentalRequestDto> returnRequests) {

        Double totalExtra = 0.0;

        for (RentalRequestDto rentalRequest : returnRequests) {

            MoviesModel movie = moviesRepository.findById(rentalRequest.getMovieId())
                    .orElseThrow(() -> new MovieNotFoundException("Movie not found: " + rentalRequest.getMovieId()));

            Integer lateDays = rentalRequest.getTotalDays();

            if (lateDays < 0) {
                throw new InvalidRentalDaysException("Late days must be zero or more.");
            }

            totalExtra = calculateLateRentalPrice(movie, totalExtra, lateDays);

        }

        return "Price for late return: " + totalExtra;
    }

    @Override
    public Double calculateRentalPrice(MoviesModel movie, Integer rentalDays) {
        
        Double price = 0.0;

        switch (movie.getType()) {
            
            case NEW:
                price = rentalDays * premiumPrice;
                break;
                
            case REGULAR:
                if (rentalDays <= 3) {
                    price = basicPrice;
                } else {
                    price = basicPrice + (rentalDays - 3) * basicPrice;
                }
                break;
                
            case OLD:
                if (rentalDays <= 5) {
                    price = basicPrice;
                } else {
                    price = basicPrice + (rentalDays - 5) * basicPrice;
                }
                break;
        }

        return price;
    }

    @Override
    public Double calculateLateRentalPrice(MoviesModel movie, Double totalExtra, Integer lateDays) {

        switch (movie.getType()) {
            
            case NEW:
                totalExtra += lateDays * premiumPrice;
                break;
                
            case REGULAR:
                totalExtra += lateDays * basicPrice;
                break;
                
            case OLD:
                totalExtra += lateDays * basicPrice;
                break;
        }

        return totalExtra;
    }
}

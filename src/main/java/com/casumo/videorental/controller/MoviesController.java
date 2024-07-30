package com.casumo.videorental.controller;

import com.casumo.videorental.dto.request.RentalRequestDto;
import com.casumo.videorental.dto.response.MoviesResponseDto;
import com.casumo.videorental.dto.response.RentalReceiptResponseDto;
import com.casumo.videorental.service.MoviesService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Miguel Castro
 */
@RestController
@RequestMapping("/api/v1")
public class MoviesController {

    @Autowired
    MoviesService moviesService;

    @Operation(description = "find all movies")
    @GetMapping("/movies")
    public ResponseEntity<List<MoviesResponseDto>> findAllMovies() {
        List<MoviesResponseDto> movies = moviesService.findAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @Operation(description = "renting movies")
    @PostMapping("/rent")
    public ResponseEntity<RentalReceiptResponseDto> rentMovies(@RequestBody List<RentalRequestDto> rentalRequestDto) {
        RentalReceiptResponseDto rentalReceipt = moviesService.rentingMovies(rentalRequestDto);
        return new ResponseEntity<>(rentalReceipt, HttpStatus.OK);
    }

    @Operation(description = "returning movies")
    @PostMapping("/return")
    public ResponseEntity<String> returnMovies(@RequestBody List<RentalRequestDto> rentalRequestDto) {
        String totalExtra = moviesService.returnMovies(rentalRequestDto);
        return new ResponseEntity<>(totalExtra, HttpStatus.OK);
    }
}

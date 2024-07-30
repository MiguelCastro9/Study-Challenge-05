package com.casumo.videorental;

import com.casumo.videorental.enums.MovieTypeEnum;
import com.casumo.videorental.model.MoviesModel;
import com.casumo.videorental.service.impl.MoviesServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculateRentalTest {

    @Autowired
    private MoviesServiceImpl moviesServiceImpl;

    @Test
    void testCalculateRentalPriceNew() {

        MoviesModel movie = new MoviesModel(1L, "Matrix 11", MovieTypeEnum.NEW);
        Double price = moviesServiceImpl.calculateRentalPrice(movie, 1);

        assertEquals(40.0, price);
    }

    @Test
    void testCalculateRentalPriceRegular() {

        MoviesModel movie = new MoviesModel(1L, "Spider Man", MovieTypeEnum.REGULAR);
        Double price = moviesServiceImpl.calculateRentalPrice(movie, 5);

        assertEquals(90.0, price);
    }

    @Test
    void testCalculateRentalPriceOld() {

        MoviesModel movie = new MoviesModel(1L, "Out of Africa", MovieTypeEnum.OLD);
        Double price = moviesServiceImpl.calculateRentalPrice(movie, 7);

        assertEquals(90.0, price);
    }

    @Test
    void testCalculateLateRentalPrice() {

        MoviesModel movie = new MoviesModel(1L, "Matrix 11", MovieTypeEnum.NEW);
        Double price = moviesServiceImpl.calculateLateRentalPrice(movie, 0.0, 2);

        assertEquals(80.0, price);
    }
}

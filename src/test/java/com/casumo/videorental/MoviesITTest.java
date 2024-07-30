package com.casumo.videorental;

import com.casumo.videorental.dto.response.MoviesResponseDto;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Miguel Castro
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MoviesITTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void findAllMoviesTest() {

        ResponseEntity<List<MoviesResponseDto>> response = restTemplate.exchange(
                "/api/v1/movies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MoviesResponseDto>>() {
        }
        );

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<MoviesResponseDto> movies = response.getBody();
        assertNotNull(movies);
    }
}

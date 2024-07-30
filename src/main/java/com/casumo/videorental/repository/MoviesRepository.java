package com.casumo.videorental.repository;

import com.casumo.videorental.model.MoviesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Miguel Castro
 */
@Repository
public interface MoviesRepository extends JpaRepository<MoviesModel, Long> {}

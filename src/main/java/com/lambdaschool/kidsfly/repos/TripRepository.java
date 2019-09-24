package com.lambdaschool.kidsfly.repos;

import com.lambdaschool.kidsfly.models.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long> {
}

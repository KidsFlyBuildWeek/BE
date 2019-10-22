package com.lambdaschool.kidsfly.services;

import com.lambdaschool.kidsfly.models.Trip;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TripService {
    List<Trip> findAll();

    Trip findTripById(long id);

    Trip save(Trip trip);

    Trip update(Trip trip, long id);
}

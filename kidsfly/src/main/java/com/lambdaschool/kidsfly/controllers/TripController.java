package com.lambdaschool.kidsfly.controllers;

import com.lambdaschool.kidsfly.models.Trip;
import com.lambdaschool.kidsfly.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class TripController {
    @Autowired
    private TripService tripservice;

    @GetMapping(value = "/trips/all", produces = {"application/json"})
    public ResponseEntity<?> listAllTrips(){
        List<Trip> allTrips = tripservice.findAll();
        return new ResponseEntity<>(allTrips, HttpStatus.OK);
    }

    @GetMapping(value ="trips/{tripId}", produces = {"application/json"})
    public ResponseEntity<?> findTripById(@PathVariable Long tripId){
        Trip p = tripservice.findTripById(tripId);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PutMapping(value ="trips/{tripId}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateTrip(@RequestBody Trip updateTrip, @PathVariable Long tripId){
        tripservice.update(updateTrip, tripId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value ="trips/new", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> addNewTrip(@Valid @RequestBody Trip newTrip) throws URISyntaxException {
        newTrip = tripservice.save(newTrip);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTripURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{tripid}").buildAndExpand(newTrip.getTripid()).toUri();
        responseHeaders.setLocation(newTripURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}

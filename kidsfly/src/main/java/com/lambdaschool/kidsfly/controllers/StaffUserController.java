package com.lambdaschool.kidsfly.controllers;

import com.lambdaschool.kidsfly.models.StaffUser;
import com.lambdaschool.kidsfly.services.StaffUserService;
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
public class StaffUserController {
    @Autowired
    private StaffUserService staffuserservice;

    @GetMapping(value = "/staff/all", produces = {"application/json"})
    public ResponseEntity<?> listAllStaffUsers(){
        List<StaffUser> allStaffUsers = staffuserservice.findAll();
        return new ResponseEntity<>(allStaffUsers, HttpStatus.OK);
    }

    @GetMapping(value ="staff/{staffId}", produces = {"application/json"})
    public ResponseEntity<?> findStaffById(@PathVariable Long staffId){
        StaffUser p = staffuserservice.findStaffUserById(staffId);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PutMapping(value ="staff/{staffId}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateStaffUser(@RequestBody StaffUser updateStaffUser, @PathVariable Long staffId){
        staffuserservice.update(updateStaffUser, staffId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value ="staff/new", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> addNewStaffUser(@Valid @RequestBody StaffUser newStaffUser) throws URISyntaxException {
        newStaffUser = staffuserservice.save(newStaffUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStaffUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{staffid}").buildAndExpand(newStaffUser.getStaffid()).toUri();
        responseHeaders.setLocation(newStaffUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}

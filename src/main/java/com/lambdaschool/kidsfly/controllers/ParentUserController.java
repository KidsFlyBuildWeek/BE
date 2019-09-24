package com.lambdaschool.kidsfly.controllers;

import com.lambdaschool.kidsfly.models.ParentUser;
import com.lambdaschool.kidsfly.services.ParentUserService;
import org.apache.coyote.Response;
import org.hibernate.annotations.Parent;
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
public class ParentUserController {

    @Autowired
    private ParentUserService parentuserservice;

    //localhost:2019/parents/all
    @GetMapping(value = "/parents/all", produces = {"application/json"})
    public ResponseEntity<?> listAllParentUsers(){
        List<ParentUser> allParentUsers = parentuserservice.findAll();
        return new ResponseEntity<>(allParentUsers, HttpStatus.OK);
    }

    //localhost:2019/parents/{id}
    @GetMapping(value = "/parents/{parentId}", produces = {"application/json"})
    public ResponseEntity<?> findParentById(@PathVariable Long parentId){
        ParentUser p = parentuserservice.findParentUserById(parentId);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PutMapping(value = "/parents/{parentId}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateParentUser(@RequestBody ParentUser updateParentUser, @PathVariable Long parentId){
        parentuserservice.update(updateParentUser, parentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/parents/parent", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> addNewParentUser(@Valid @RequestBody ParentUser newParentUser) throws URISyntaxException {
        newParentUser = parentuserservice.save(newParentUser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newParentUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{parentid}").buildAndExpand(newParentUser.getParentid()).toUri();
        responseHeaders.setLocation(newParentUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}

package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {
    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> findWhiskyByYear(
            @RequestParam(name="year", required = false) Integer year,
            @RequestParam(name="age", required = false) Integer age,
            @RequestParam(name="region", required = false) String region,
            @RequestParam(name="distillery", required = false) Distillery distillery){

        if(year != null){
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        else if(distillery != null && age  != null) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryAndAge(distillery, age), HttpStatus.OK);
        }
        else if(region != null) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
        }
    }

//    @GetMapping(value = "/")
}

//    @GetMapping(value = "/ships/pirates")
//    public ResponseEntity<List<Ship>> findShipsThatHavePiratesNamedQueryString(
//            @RequestParam(name="named") String name){
//        return new ResponseEntity<>(shipRepository.findByPiratesFirstName(name), HttpStatus.OK);
//    }



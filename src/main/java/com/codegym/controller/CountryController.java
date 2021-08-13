package com.codegym.controller;

import com.codegym.model.Country;
import com.codegym.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private ICountryService countryService;

    @GetMapping("")
    public ResponseEntity<Iterable<Country>> findAll(){
        Iterable<Country> countries = countryService.findAll();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        return new ResponseEntity<>(countryService.save(country), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country> findOne(@PathVariable Long id){
        return new ResponseEntity<>(countryService.findById(id).get(),HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Long id,@RequestBody Country country){
        Optional<Country> countryOptional = countryService.findById(id);
        if (!countryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            country.setId(id);
            countryService.save(country);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable Long id){
        Optional<Country> productOptional = countryService.findById(id);
        if (!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        countryService.remove(id);
        return new ResponseEntity<>(productOptional.get(),HttpStatus.NO_CONTENT);
    }

}
package com.codegym.service.city;

import com.codegym.model.City;
import com.codegym.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CityService implements ICityService {
    @Autowired
    ICityRepository iCityRepository;
    @Override
    public Iterable findAll() {
        return iCityRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return iCityRepository.findById(id);
    }

    @Override
    public City  save(City city) {
        return iCityRepository.save(city);
    }

    @Override
    public void remove(Long id) {
        iCityRepository.deleteById(id);
    }
}

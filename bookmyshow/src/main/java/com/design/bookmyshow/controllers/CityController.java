package com.design.bookmyshow.controllers;


import com.design.bookmyshow.models.City;
import com.design.bookmyshow.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CityController {
    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    public City addCity(String name) {
        return this.cityService.addCity(name);
    }
}

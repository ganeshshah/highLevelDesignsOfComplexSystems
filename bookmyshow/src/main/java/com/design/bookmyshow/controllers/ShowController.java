package com.design.bookmyshow.controllers;


import com.design.bookmyshow.models.Language;
import com.design.bookmyshow.models.SeatType;
import com.design.bookmyshow.models.Show;
import com.design.bookmyshow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Map;

@Controller
public class ShowController {

    private ShowService showService;

    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    public Show createShow(
            Long movieId,
            Date startTime,
            Date endTime,
            Long auditoriumId,
            Map<SeatType, Integer> seatPricing,
            Language language
    ) {
        return showService.createShow(
                movieId, startTime, endTime, auditoriumId, seatPricing, language);
    }

}

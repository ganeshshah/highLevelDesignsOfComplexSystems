package com.lowleveldesign.parkinglot.controllers;

import com.lowleveldesign.parkinglot.dtos.CreateParkingLotRequestDto;
import com.lowleveldesign.parkinglot.dtos.CreateParkingLotResponseDto;
import com.lowleveldesign.parkinglot.dtos.ResponseStatusDto;
import com.lowleveldesign.parkinglot.models.ParkingFloor;
import com.lowleveldesign.parkinglot.models.ParkingLot;
import com.lowleveldesign.parkinglot.services.ParkingLotService;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotController {

    private ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public CreateParkingLotResponseDto createParkingLot(
            CreateParkingLotRequestDto request
    ) {

        if (request.getNumberOfFloors() < 2) {
            CreateParkingLotResponseDto responseDto = new CreateParkingLotResponseDto();
            responseDto.setResponseStatus(ResponseStatusDto.FAILURE);
            return responseDto;
        }

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress(request.getAddress());
        List<ParkingFloor> parkingFloors = new ArrayList<>();
        for (int i = 0; i < request.getNumberOfFloors(); ++i) {
            parkingFloors.add(new ParkingFloor());
        }
        parkingLot.setParkingFloor(parkingFloors);

        ParkingLot createdParkingLot = parkingLotService.createParkingLot(parkingLot);

        CreateParkingLotResponseDto response = new CreateParkingLotResponseDto();
        response.setParkingLot(createdParkingLot);
        response.setResponseStatus(ResponseStatusDto.SUCCESS);
        return response;
    }




}

package com.example.AEPB.parkinglot;

import com.example.AEPB.parkinglot.exception.NoFreeParkingSpaceException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {


    SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }


    public Ticket parking(Car car) {
        Optional<ParkingLot> optionalParkingLot = parkingLots.stream().max(Comparator.comparingInt(ParkingLot::getSpaceCount));
        if (optionalParkingLot.isPresent()) {
            ParkingLot parkingLot = optionalParkingLot.get();
            Ticket ticket = parkingLot.parking(car);
            ticketParkingLotMap.put(ticket, parkingLot);
            return ticket;
        } else {
            throw new NoFreeParkingSpaceException();
        }
    }

}

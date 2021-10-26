package com.example.AEPB.parkinglot;

import com.example.AEPB.parkinglot.exception.NoFreeParkingSpaceException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ParkingRobot {

    protected List<ParkingLot> parkingLots;

    protected Map<Ticket, ParkingLot> ticketParkingLotMap = new HashMap<>();

    ParkingRobot(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    protected int getParkingLotNumberByTicket(Ticket ticket) {
        return parkingLots.indexOf(ticketParkingLotMap.get(ticket));
    }

    public Ticket parking(Car car) {
        Optional<ParkingLot> parkingLotOptional = parkingLots.stream().max(Comparator.comparingDouble(ParkingLot::getVacancyRate));
        if (parkingLotOptional.isPresent()) {
            ParkingLot parkingLot = parkingLotOptional.get();
            Ticket ticket = parkingLot.parking(car);
            ticketParkingLotMap.put(ticket, parkingLot);
            return ticket;
        } else {
            throw new NoFreeParkingSpaceException();
        }
    }
}

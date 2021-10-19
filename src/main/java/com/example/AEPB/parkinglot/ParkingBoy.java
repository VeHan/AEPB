package com.example.AEPB.parkinglot;

import com.example.AEPB.parkinglot.exception.NoCarMatchedFoundException;
import com.example.AEPB.parkinglot.exception.NoFreeParkingSpaceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    private Map<Ticket, ParkingLot> ticketParkingLotMap = new HashMap<>();

    ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    private int getParkingLotNumberByTicket(Ticket ticket) {
        return parkingLots.indexOf(ticketParkingLotMap.get(ticket));
    }

    public Ticket parking(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                Ticket ticket = parkingLot.parking(car);
                ticketParkingLotMap.put(ticket, parkingLot);
                return ticket;
            } catch (NoFreeParkingSpaceException ignored) {
            }
        }
        throw new NoFreeParkingSpaceException();
    }

    public Car pickUp(Ticket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                Car car = parkingLot.pickUp(ticket);
                ticketParkingLotMap.remove(ticket);
                return car;
            } catch (NoCarMatchedFoundException ignored) {
            }
        }
        throw new NoCarMatchedFoundException();
    }

}

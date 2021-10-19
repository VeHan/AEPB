package com.example.AEPB.parkinglot;

import com.example.AEPB.parkinglot.exception.NoCarMatchedFoundException;
import com.example.AEPB.parkinglot.exception.NoFreeParkingSpaceException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class ParkingLot {

    private static final int COUNT_OF_PARKING_SPACES = 50;
    private final LinkedList<Ticket> tickets;
    private final Map<Ticket, Car> ticketCarMap;


    public ParkingLot() {
        tickets = new LinkedList<>();
        ticketCarMap = new HashMap<>();
        for (int i = 0; i < COUNT_OF_PARKING_SPACES; i++) {
            tickets.add(new Ticket());
        }
    }

    public synchronized Ticket parking(Car car) {
        Ticket ticket = tickets.poll();
        if (ticket == null) {
            throw new NoFreeParkingSpaceException();
        }
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public synchronized Car pickUp(Ticket ticket) {
        Car car = ticketCarMap.remove(ticket);
        if (car == null) {
            throw new NoCarMatchedFoundException();
        }
        tickets.add(ticket);
        return car;
    }

}

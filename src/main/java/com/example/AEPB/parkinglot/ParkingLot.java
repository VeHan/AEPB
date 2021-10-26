package com.example.AEPB.parkinglot;

import com.example.AEPB.parkinglot.exception.NoCarMatchedFoundException;
import com.example.AEPB.parkinglot.exception.NoFreeParkingSpaceException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class ParkingLot {

    private final LinkedList<Ticket> tickets;
    private final Map<Ticket, Car> ticketCarMap;
    private final int parkingSpaceCount;


    public ParkingLot(int parkingSpaceCount) {
        if (parkingSpaceCount <= 0 || parkingSpaceCount >= 100000) {
            throw new IllegalArgumentException("parkingSpaceCount should be 0-100000");
        }
        this.parkingSpaceCount = parkingSpaceCount;
        tickets = new LinkedList<>();
        ticketCarMap = new HashMap<>();
        for (int i = 0; i < parkingSpaceCount; i++) {
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

    protected int getSpaceCount() {
        return tickets.size();
    }

    protected float getVacancyRate() {
        return tickets.size() / (float) this.parkingSpaceCount;
    }

}

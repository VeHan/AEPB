package com.example.AEPB.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotGroup {

    private List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingLotGroup() {
        for (int i = 0; i < 10; i++) {
            parkingLots.add(new ParkingLot());
        }
    }

    private final ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

    public ParkingBoy getParkingBoy() {
        return parkingBoy;
    }

    public Ticket parkingByBoy(Car car) {
        return parkingBoy.parking(car);
    }

    public Car pickUpByBoy(Ticket ticket) {
        return parkingBoy.pickUp(ticket);
    }
}

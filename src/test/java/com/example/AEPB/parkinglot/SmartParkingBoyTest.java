package com.example.AEPB.parkinglot;

import com.example.AEPB.parkinglot.exception.NoFreeParkingSpaceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SmartParkingBoyTest {

    private List<ParkingLot> parkingLots = new ArrayList<>();
    private SmartParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        parkingLots.clear();
        for (int i = 0; i < 10; i++) {
            parkingLots.add(new ParkingLot(50));
        }
        parkingBoy = new SmartParkingBoy(parkingLots);
    }

    private void preParking(ParkingLot parkingLot, int number) {
        for (int i = 0; i < number; i++) {
            parkingLot.parking(new Car());
        }
    }


    @Test
    void should_return_ticket_when_parking_by_boy_given_all_parking_lot_is_empty() {

        // given
        Car car = new Car();

        // when
        Ticket ticket = parkingBoy.parking(car);

        // then
        assertNotNull(ticket);
    }

    @Test
    void should_return_ticket_and_parking_at_lot2_when_parking_by_boy_given_lot_2_has_the_largest_number_of_space() {
        // given
        preParking(parkingLots.get(0), 10);
        preParking(parkingLots.get(1), 9);
        preParking(parkingLots.get(2), 10);
        preParking(parkingLots.get(3), 10);
        preParking(parkingLots.get(4), 10);
        preParking(parkingLots.get(5), 10);
        preParking(parkingLots.get(6), 10);
        preParking(parkingLots.get(7), 10);
        preParking(parkingLots.get(8), 10);
        preParking(parkingLots.get(9), 10);

        // when
        Ticket ticket = parkingBoy.parking(new Car());

        // then
        assertNotNull(ticket);
        assertEquals(1, parkingBoy.getParkingLotNumberByTicket(ticket));
    }

    @Test
    void should_return_ticket_and_parking_at_lot2_when_parking_by_boy_given_lot_2_and_lot_4_has_the_largest_number_of_space() {
        // given
        preParking(parkingLots.get(0), 10);
        preParking(parkingLots.get(1), 9);
        preParking(parkingLots.get(2), 10);
        preParking(parkingLots.get(3), 9);
        preParking(parkingLots.get(4), 10);
        preParking(parkingLots.get(5), 10);
        preParking(parkingLots.get(6), 10);
        preParking(parkingLots.get(7), 10);
        preParking(parkingLots.get(8), 10);
        preParking(parkingLots.get(9), 10);

        // when
        Ticket ticket = parkingBoy.parking(new Car());

        // then
        assertNotNull(ticket);
        assertEquals(1, parkingBoy.getParkingLotNumberByTicket(ticket));
    }

    @Test
    void should_throw_NoFreeParkingSpaceException_and_parking_at_lot2_when_parking_by_boy_given_all_lot_is_full() {
        // given
        for (int i = 0; i < 500; i++) {
            parkingBoy.parking(new Car());
        }
        Car car = new Car();

        // when
        // then
        assertThrows(NoFreeParkingSpaceException.class, () -> parkingBoy.parking(car));
    }


}

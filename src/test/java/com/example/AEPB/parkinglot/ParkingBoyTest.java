package com.example.AEPB.parkinglot;

import com.example.AEPB.parkinglot.exception.NoCarMatchedFoundException;
import com.example.AEPB.parkinglot.exception.NoFreeParkingSpaceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingBoyTest {

    private List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        parkingLots.clear();
        for (int i = 0; i < 10; i++) {
            parkingLots.add(new ParkingLot(50));
        }
        parkingBoy = new ParkingBoy(parkingLots);
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
    void should_return_ticket_and_parking_at_lot2_when_parking_by_boy_given_lot_1_is_full_and_lot_2_has_space() {
        // given
        for (int i = 0; i < 50; i++) {
            parkingBoy.parking(new Car());
        }
        Car car = new Car();

        // when
        Ticket ticket = parkingBoy.parking(car);

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

    @Test
    void should_return_car_when_pick_up_by_boy_given_has_car_matches_the_ticket() {
        // given
        for (int i = 0; i < 201; i++) {
            parkingBoy.parking(new Car());
        }
        Car car = new Car();
        Ticket ticket = parkingBoy.parking(car);

        // when
        Car pickedCat = parkingBoy.pickUp(ticket);
        // then
        assertEquals(car, pickedCat);
    }

    @Test
    void should_throw_NoCarMatchedFoundException_when_pick_up_by_boy_given_no_car_matches_the_ticket() {
        // given
        Ticket ticket = new Ticket();

        // when
        // then
        assertThrows(NoCarMatchedFoundException.class, () -> parkingBoy.pickUp(ticket));
    }

}

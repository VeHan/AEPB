package com.example.AEPB.parkinglot;

import com.example.AEPB.parkinglot.exception.NoCarMatchedFoundException;
import com.example.AEPB.parkinglot.exception.NoFreeParkingSpaceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotTest {
    private ParkingLot parkingLot;

    @BeforeEach
    void setUp(){
        parkingLot = new ParkingLot(50);
    }

    @Test
    void should_parking_success_when_parking_given_parking_lot_not_full() {
        // given
        Car car = new Car();
        // when
        Ticket ticket = parkingLot.parking(car);
        // then
        assertNotNull(ticket);
    }


    @Test
    void should_pickup_success_when_pickup_given_parking_lot_has_the_car() {
        // given
        Car parkingCar = new Car();
        Ticket ticket = parkingLot.parking(parkingCar);
        // when
        Car car = parkingLot.pickUp(ticket);
        // then
        assertNotNull(car);
    }


    @Test
    void should_throw_NoFreeParkingSpaceException_when_parking_given_parking_lot_is_full() {
        // given
        for (int i = 0; i < 50; i++) {
            parkingLot.parking(new Car());
        }
        // when
        // then
        assertThrows(NoFreeParkingSpaceException.class, () -> parkingLot.parking(new Car()));
    }

    @Test
    void should_throw_NoCarMatchedFoundException_when_pickup_given_parking_does_not_has_the_car() {
        // given
        Ticket ticket = new Ticket();
        // when
        // then
        assertThrows(NoCarMatchedFoundException.class, () -> parkingLot.pickUp(ticket));
    }

    @Test
    void should_throw_NoCarMatchedFoundException_when_pickup_multiple_given_parking_has_the_car() {
        // given
        Ticket ticket = parkingLot.parking(new Car());
        // when
        // then
        parkingLot.pickUp(ticket);
        assertThrows(NoCarMatchedFoundException.class, () -> parkingLot.pickUp(ticket));
    }
}

package com.example.AEPB.parkinglot;

import com.example.AEPB.parkinglot.exception.NoCarMatchedFoundException;
import com.example.AEPB.parkinglot.exception.NoFreeParkingSpaceException;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotGroupTest {

    private Method getParkingLotNumberByTicket = ReflectionUtils.getRequiredMethod(ParkingBoy.class, "getParkingLotNumberByTicket", Ticket.class);

    @Test
    void should_return_ticket_when_parking_by_boy_given_all_parking_lot_is_empty() {
        // given
        ParkingLotGroup parkingLotGroup = new ParkingLotGroup();
        Car car = new Car();

        // when
        Ticket ticket = parkingLotGroup.parkingByBoy(car);

        // then
        assertNotNull(ticket);
    }

    @Test
    void should_return_ticket_and_parking_at_lot2_when_parking_by_boy_given_lot_1_is_full_and_lot_2_has_space() {
        // given
        ParkingLotGroup parkingLotGroup = new ParkingLotGroup();
        for (int i = 0; i < 50; i++) {
            parkingLotGroup.parkingByBoy(new Car());
        }
        Car car = new Car();

        // when
        Ticket ticket = parkingLotGroup.parkingByBoy(car);

        // then
        assertNotNull(ticket);
        assertEquals(1, ReflectionUtils.invokeMethod(getParkingLotNumberByTicket, parkingLotGroup.getParkingBoy(), ticket));
    }

    @Test
    void should_throw_NoFreeParkingSpaceException_and_parking_at_lot2_when_parking_by_boy_given_all_lot_is_full() {
        // given
        ParkingLotGroup parkingLotGroup = new ParkingLotGroup();
        for (int i = 0; i < 500; i++) {
            parkingLotGroup.parkingByBoy(new Car());
        }
        Car car = new Car();

        // when
        // then
        assertThrows(NoFreeParkingSpaceException.class, () -> parkingLotGroup.parkingByBoy(car));
    }

    @Test
    void should_return_car_when_pick_up_by_boy_given_has_car_matches_the_ticket() {
        // given
        ParkingLotGroup parkingLotGroup = new ParkingLotGroup();
        for (int i = 0; i < 201; i++) {
            parkingLotGroup.parkingByBoy(new Car());
        }
        Car car = new Car();
        Ticket ticket = parkingLotGroup.parkingByBoy(car);

        // when
        Car pickedCat = parkingLotGroup.pickUpByBoy(ticket);
        // then
        assertEquals(car, pickedCat);
    }

    @Test
    void should_throw_NoCarMatchedFoundException_when_pick_up_by_boy_given_no_car_matches_the_ticket() {
        // given
        ParkingLotGroup parkingLotGroup = new ParkingLotGroup();
        Ticket ticket = new Ticket();

        // when
        // then
        assertThrows(NoCarMatchedFoundException.class, () -> parkingLotGroup.pickUpByBoy(ticket));
    }

}

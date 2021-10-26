package com.example.AEPB.parkinglot;

import com.example.AEPB.parkinglot.exception.NoFreeParkingSpaceException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingRobotTest {

    private List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingRobot parkingRobot;

    void generateRobot(int[] parkingLogSpaceCounts) {
        parkingLots.clear();
        for (int parkingLogSpaceCount : parkingLogSpaceCounts) {
            parkingLots.add(new ParkingLot(parkingLogSpaceCount));
        }
        parkingRobot = new ParkingRobot(parkingLots);
    }

    private void preParking(ParkingLot parkingLot, int number) {
        for (int i = 0; i < number; i++) {
            parkingLot.parking(new Car());
        }
    }


    @Test
    void should_return_ticket_when_parking_by_boy_given_all_parking_lot_is_empty() {

        // given
        generateRobot(new int[]{10, 10, 10, 10, 10});
        Car car = new Car();

        // when
        Ticket ticket = parkingRobot.parking(car);

        // then
        assertNotNull(ticket);
    }

    @Test
    void should_return_ticket_and_parking_at_lot2_when_parking_by_boy_given_lot_2_has_the_largest_vacancy_rate() {
        // given
        generateRobot(new int[]{10, 20, 30, 40, 50});
        preParking(parkingLots.get(0), 5);  //50%
        preParking(parkingLots.get(1), 9);  //55%
        preParking(parkingLots.get(2), 15); //50%
        preParking(parkingLots.get(3), 20); //50%
        preParking(parkingLots.get(4), 25); //50%

        // when
        Ticket ticket = parkingRobot.parking(new Car());

        // then
        assertNotNull(ticket);
        assertEquals(1, parkingRobot.getParkingLotNumberByTicket(ticket));
    }

    @Test
    void should_return_ticket_and_parking_at_lot2_when_parking_by_boy_given_lot_2_and_lot_4_has_the_largest_number_of_space() {
        // given
        generateRobot(new int[]{10, 20, 30, 40, 50});
        preParking(parkingLots.get(0), 5);  // 50%
        preParking(parkingLots.get(1), 5);  // 75%
        preParking(parkingLots.get(2), 15); // 50%
        preParking(parkingLots.get(3), 10); // 75%
        preParking(parkingLots.get(4), 25); // 50%

        // when
        Ticket ticket = parkingRobot.parking(new Car());

        // then
        assertNotNull(ticket);
        assertEquals(1, parkingRobot.getParkingLotNumberByTicket(ticket));
    }

    @Test
    void should_throw_NoFreeParkingSpaceException_and_parking_at_lot2_when_parking_by_boy_given_all_lot_is_full() {
        // given
        generateRobot(new int[]{10, 20, 30, 40, 50});
        preParking(parkingLots.get(0), 10);
        preParking(parkingLots.get(1), 20);
        preParking(parkingLots.get(2), 30);
        preParking(parkingLots.get(3), 40);
        preParking(parkingLots.get(4), 50);
        Car car = new Car();

        // when
        // then
        assertThrows(NoFreeParkingSpaceException.class, () -> parkingRobot.parking(car));
    }


}

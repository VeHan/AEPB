package com.example.AEPB;

import com.example.AEPB.exception.AmountOutOfRangeException;
import com.example.AEPB.exception.CannotCompareException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AirCoinTest {

    @Test
    void should_return_true_when_compare_given_an_aircoin_with_amount_1_and_an_aircoin_with_amount_1() {
        AirCoin airCoinA = new AirCoin(1);
        AirCoin airCoinB = new AirCoin(1);
        assertTrue(airCoinA.compare(airCoinB));
    }

    @Test
    void should_return_false_when_compare_given_an_aircoin_with_amount_1_and_an_aircoin_with_amount_2() {
        AirCoin airCoinA = new AirCoin(1);
        AirCoin airCoinB = new AirCoin(2);
        assertFalse(airCoinA.compare(airCoinB));
    }

    @Test
    void should_throw_CannotCompareException_when_compare_given_an_aircoin_with_amount_1_and_an_aircoin_is_null() {
        AirCoin airCoinA = new AirCoin(1);
        AirCoin airCoinB = null;
        assertThrows(CannotCompareException.class, () -> airCoinA.compare(airCoinB));
    }

    @Test
    void should_throw_AmountOutOfRangeException_when_create_an_aircoin_given_amount_is_negative_1() {
        assertThrows(AmountOutOfRangeException.class, () -> new AirCoin(-1));
    }

    @Test
    void should_throw_AmountOutOfRangeException_when_create_an_aircoin_given_amount_is_1000000001() {
        assertThrows(AmountOutOfRangeException.class, () -> new AirCoin(1000000001));
    }
}

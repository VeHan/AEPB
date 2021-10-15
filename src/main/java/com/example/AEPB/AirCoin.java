package com.example.AEPB;

import com.example.AEPB.exception.AmountOutOfRangeException;
import com.example.AEPB.exception.CannotCompareException;

public class AirCoin {

    public static final int MAX_AMOUNT = 1000000000;
    public static final int MIN_AMOUNT = 0;
    private int amount;

    public AirCoin(int amount) throws AmountOutOfRangeException {
        this.setAmount(amount);
    }

    public void setAmount(int amount) throws AmountOutOfRangeException {
        if (amount < MIN_AMOUNT || amount > MAX_AMOUNT){
            throw new AmountOutOfRangeException();
        }
        this.amount = amount;
    }

    public boolean compare(AirCoin coin) throws CannotCompareException {
        if (coin == null){
            throw new CannotCompareException();
        }
        return this.amount == coin.amount;
    }
}
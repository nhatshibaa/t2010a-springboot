package com.example.t2010aspringboot.enums;

public enum OrderStatus {
    PENDING(2), PROCESSING(0), DONE(1), CANCEL(-1);

    public final Integer orderStatus;

    OrderStatus(Integer orderStatus){
        this.orderStatus = orderStatus;
    }
}

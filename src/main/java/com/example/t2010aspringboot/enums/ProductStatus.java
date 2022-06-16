package com.example.t2010aspringboot.enums;

public enum ProductStatus {
    ACTIVE(1), DEACTIVE(-1), PENDING(2), UNDEFINED(0);

    public final Integer productStatus;

    ProductStatus(Integer productStatus){
        this.productStatus = productStatus;
    }
}

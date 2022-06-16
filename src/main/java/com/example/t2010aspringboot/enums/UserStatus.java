package com.example.t2010aspringboot.enums;

public enum UserStatus {
    ACTIVE(1), DEACTIVE(-1), PENDING(2), UNDEFINED(0);

    public final Integer userStatus;

    UserStatus(Integer userStatus){
        this.userStatus = userStatus;
    }
}

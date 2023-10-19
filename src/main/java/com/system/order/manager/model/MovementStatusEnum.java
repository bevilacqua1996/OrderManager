package com.system.order.manager.model;

import com.system.order.manager.exception.NoDataFoundException;

public enum MovementStatusEnum {

    PACKING(1, "PACKING"),
    IN_TRANSIT(2, "IN TRANSIT"),
    DELIVERED(3, "DELIVERED");

    private int code;
    private String status;

    MovementStatusEnum(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public static MovementStatusEnum ofCodeNumber(int code) {
        for(MovementStatusEnum codeEnum : MovementStatusEnum.values()) {
            if(codeEnum.getCode() == code) {
                return codeEnum;
            }
        }
        throw new NoDataFoundException( "Movement Status code not found: " + String.valueOf(code) );
    }

    public static MovementStatusEnum ofStatus(String status) {
        for(MovementStatusEnum codeEnum : MovementStatusEnum.values()) {
            if(codeEnum.getStatus().equals(status)) {
                return codeEnum;
            }
        }
        throw new NoDataFoundException( "Movement Status not found: " + String.valueOf(status) );
    }
}

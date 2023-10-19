package com.system.order.manager.model;

import com.system.order.manager.exception.NoDataFoundException;

public enum OrderStatusEnum {

    OPEN(1, "OPEN"),
    CLOSED(2, "CLOSED");

    private int code;
    private String status;

    OrderStatusEnum(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public static OrderStatusEnum ofCodeNumber(int code) {
        for(OrderStatusEnum codeEnum : OrderStatusEnum.values()) {
            if(codeEnum.getCode() == code) {
                return codeEnum;
            }
        }
        throw new NoDataFoundException( "Order Status code not found: " + String.valueOf(code) );
    }

    public static OrderStatusEnum ofStatus(String status) {
        for(OrderStatusEnum codeEnum : OrderStatusEnum.values()) {
            if(codeEnum.getStatus().equals(status)) {
                return codeEnum;
            }
        }
        throw new NoDataFoundException( "Order Status not found: " + String.valueOf(status) );
    }
}

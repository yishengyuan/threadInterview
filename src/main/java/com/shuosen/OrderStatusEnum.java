package com.shuosen;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * 后台订单状态枚举
 * @author  christ
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {


    NOT_SETTLE(1,"未结算"),
    SETTLED(2, "已结算"),
    CANCELED(3,"已取消"),
    INVALID_ORDER(4,"无效订单"),
    ;

    Integer code ;
    String msg;


    public static OrderStatusEnum getByCode(Integer code) {
        for (OrderStatusEnum e : OrderStatusEnum.values()) {
            if(e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }


}

package com.shuosen;

import org.junit.Test;

import java.util.Optional;

public class LambdaOptional {

    @Test
    public void testOptional(){
        String o = Optional.ofNullable(null).map(x -> "已结算").orElse("未结算");

        System.out.println(o);

    }
}

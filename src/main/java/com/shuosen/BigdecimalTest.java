package com.shuosen;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class BigdecimalTest {
    @Test
    public void BigdecimaTest(){
        BigDecimal hundred = new BigDecimal(100);
        BigDecimal zero = new BigDecimal(0);
        System.out.println(hundred.compareTo(new BigDecimal(0)));
        System.out.println(hundred.compareTo(new BigDecimal(100)));
        System.out.println(zero.compareTo(new BigDecimal(100)));
        System.out.println(zero.compareTo(new BigDecimal(0)) >0);

    }

    @Test
    public void testJSON(){
        String[] split = "2147634928,".split(",");
        System.out.println(split[0]);
        if (split.length >0){
            List<String> list = Arrays.asList(split);
            System.out.println(list.size());
            System.out.println(list);

        }

    }
}

package com.shuosen;

import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalDemo {

    @Test
    public void test1(){
        Float f = 0.01F;
        Double d = 0.01D;
        System.out.println(f);//0.01
        System.out.println(d);//0.01
        BigDecimal bigDecimal1 = new BigDecimal(f);
        BigDecimal bigDecimal2 = new BigDecimal(Float.toString(f));
        BigDecimal bigDecimal3 = new BigDecimal(d);
        BigDecimal bigDecimal4 = new BigDecimal(Double.toString(d));
        System.out.println(bigDecimal1);//0.00999999977648258209228515625
        System.out.println(bigDecimal1.floatValue());//0.01
        System.out.println(bigDecimal2);//0.01
        System.out.println(bigDecimal3);//0.01000000000000000020816681711721685132943093776702880859375
        System.out.println(bigDecimal3.doubleValue());//0.01
        System.out.println(bigDecimal4);//0.01


//        System.out.println(new BigDecimal(Float.toString(100000000000f)));


        System.out.println(BigDecimalUtils.floatAdd(123f, 100000000000f));
    }

}

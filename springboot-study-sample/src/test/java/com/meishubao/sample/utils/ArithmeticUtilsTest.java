package com.meishubao.sample.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ArithmeticUtilsTest {

    @Test
    void add() {
        System.out.println(ArithmeticUtils.add(23.56, 45.689));
    }

    @Test
    void testAdd() {
        System.out.println(ArithmeticUtils.add("23.56", "45.689"));
    }

    @Test
    void testAdd1() {
        System.out.println(ArithmeticUtils.add("23.56", "45.689", 2));
    }

    @Test
    void sub() {
        System.out.println(ArithmeticUtils.sub(23.56, 45.689));
    }

    @Test
    void testSub() {
        System.out.println(ArithmeticUtils.sub("23.56", "45.689"));
    }

    @Test
    void testSub1() {
        System.out.println(ArithmeticUtils.sub("23.56", "45.689", 2));
    }

    @Test
    void mul() {
        System.out.println(ArithmeticUtils.mul(23.56, 45.689));
    }

    @Test
    void testMul() {
        System.out.println(ArithmeticUtils.mul("23.56", "45.689"));
    }

    @Test
    void testMul1() {
        System.out.println(ArithmeticUtils.mul(23.56, 45.689, 2));
    }

    @Test
    void testMul2() {
        System.out.println(ArithmeticUtils.mul("23.56", "45.689", 2));
    }

    @Test
    void div() {
        System.out.println(ArithmeticUtils.div(23.56, 45.689));
    }

    @Test
    void testDiv() {
        System.out.println(ArithmeticUtils.div(23.56, 45.689, 2));
    }

    @Test
    void testDiv1() {
        System.out.println(ArithmeticUtils.div("23.56", "45.689", 2));
    }

    @Test
    void percent() {
        System.out.println(ArithmeticUtils.percent(23.56, 45.689));
    }

    @Test
    void testPercent() {
        System.out.println(ArithmeticUtils.percent(23.56, 45.689, 2));
    }

    @Test
    void testPercent1() {
        System.out.println(ArithmeticUtils.percent("23.56", "45.689"));
    }

    @Test
    void testPercent2() {
        System.out.println(ArithmeticUtils.percent("23.56", "45.689", 2));
    }

    @Test
    void round() {
        System.out.println(ArithmeticUtils.round(45.68932649, 3));
    }

    @Test
    void testRound() {
        System.out.println(ArithmeticUtils.round("45.68932649", 3));
    }

    @Test
    void remainder() {
        System.out.println(ArithmeticUtils.remainder("23.56", "45.689", 3));
    }

    @Test
    void testRemainder() {
        System.out.println(ArithmeticUtils.remainder(new BigDecimal("23.56"), new BigDecimal("45.689"), 3));
    }

    @Test
    void compare() {
        System.out.println(ArithmeticUtils.compare("23.56", "45.689"));
    }

}
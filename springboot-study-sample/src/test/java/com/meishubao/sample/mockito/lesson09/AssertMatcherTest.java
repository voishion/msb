package com.meishubao.sample.mockito.lesson09;


import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author lilu
 */
public class AssertMatcherTest {

    @Test
    public void test1() {
        int i = 10;
        // 等于
        assertThat(i, equalTo(10));
        assertThat(i, is(10));
        // 不等于
        assertThat(i, not(equalTo(20)));
        assertThat(i, is(not(20)));
    }

    @Test
    public void test2() {
        double price = 23.45D;
        // 或
        assertThat(price, either(equalTo(23.45)).or(equalTo(23.54)));
        assertThat(price, anyOf(is(23.45), is(23.56), is(12.45), not(56.78)));
        // 并
        assertThat(price, both(equalTo(23.45)).and(not(equalTo(23.54))));
        assertThat(price, allOf(is(23.45), not(is(23.56)), not(is(12.45)), not(56.78)));

        assertThat(Stream.of(1,2,3).anyMatch(i -> i > 2), equalTo(true));
        assertThat(Stream.of(1,2,3).allMatch(i -> i > 0), equalTo(true));
    }

    @Test
    public void test3() {
        double price = 23.45D;
        // 断言错误原因
        assertThat("the double value assertion failed", price, either(equalTo(23.65)).or(equalTo(23.54)));
    }

}

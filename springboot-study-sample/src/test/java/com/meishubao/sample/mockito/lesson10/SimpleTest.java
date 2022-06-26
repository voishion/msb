package com.meishubao.sample.mockito.lesson10;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.meishubao.sample.mockito.lesson10.NumberCompareMatcher.*;

/**
 * @author lilu
 */
public class SimpleTest {

    @Test
    public void test() {
        assertThat(1, lt(3));
        assertThat(10, gt(3));
        assertThat(12, both(gt(10)).and(lt(13)));

        assertThat(1, lte(1));
        assertThat(10, gte(10));
        assertThat(12, both(gte(12)).and(lte(12)));
    }

}

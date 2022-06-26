package com.meishubao.sample.mockito.lesson06;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

public class DemoTest1 {

    @Spy
    private Demo demo;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() {
        reset(demo);
    }

    @Test
    public void add() {
        int result = demo.add(1, 2);
        assertThat(result, equalTo(3));

        when(demo.add(2, 3)).thenReturn(0);
        result = demo.add(2, 3);
        assertThat(result, equalTo(0));
    }

}
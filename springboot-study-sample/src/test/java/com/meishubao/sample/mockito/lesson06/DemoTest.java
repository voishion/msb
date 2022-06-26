package com.meishubao.sample.mockito.lesson06;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

public class DemoTest {

    @Spy
    private Demo demo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        reset(demo);
    }

    @Test
    void add() {
        int result = demo.add(1, 2);
        assertThat(result, equalTo(3));

        when(demo.add(2, 3)).thenReturn(0);
        result = demo.add(2, 3);
        assertThat(result, equalTo(0));
    }

}
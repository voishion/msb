package com.meishubao.sample.mockito.lesson08;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @author lilu
 */
//@RunWith(MockitoJUnitRunner.class)
public class WildcardArgumentsMatcherTest {

    @Mock
    private SimpleService simpleService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void wildcardMethod1() {
        when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(100);
        int result = simpleService.method1(1, "Alex", Collections.emptyList(), "Mockito");
        assertThat(result, equalTo(100));
        result = simpleService.method1(1, "ZhaoSi", Collections.emptySet(), "MockitoForJava");
        assertThat(result, equalTo(100));
    }

    @Test
    public void wildcardMethod1WithSpec() {
        // 范围大的写前面，范围小的写后面
        when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(-1);
        when(simpleService.method1(anyInt(), eq("Alex"), anyCollection(), isA(Serializable.class))).thenReturn(100);
        when(simpleService.method1(anyInt(), eq("Zhao"), anyCollection(), isA(Serializable.class))).thenReturn(200);

        int result = simpleService.method1(1, "Alex", Collections.emptyList(), "Mockito");
        assertThat(result, equalTo(100));

        result = simpleService.method1(1, "Zhao", Collections.emptySet(), "MockitoForJava");
        assertThat(result, equalTo(200));

        result = simpleService.method1(1, "Liu", Collections.emptySet(), "MockitoForJava");
        assertThat(result, equalTo(-1));
    }

    @Test
    public void wildcardMethod2() {
        doNothing().when(simpleService).method2(anyInt(), anyString(), anyCollection(), isA(Serializable.class));
        List<Object> list = Collections.emptyList();
        simpleService.method2(1, "Alex", list, "Mockito");
        verify(simpleService, times(1)).method2(1, "Alex", list, "Mockito");
        verify(simpleService, times(1)).method2(anyInt(), eq("Alex"), anyCollection(), isA(Serializable.class));
    }

    @After
    public void destroy() {
        reset(simpleService);
    }
}

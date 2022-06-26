package com.meishubao.sample.mockito.lesson06;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * spy jdk17 中应用有问题
 *
 * @author lilu
 */
public class SpyingAnnotationTest {

    //@InjectMocks
    //@Resource
    @Spy
    private List<String> list = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSpy() {
        list.add("Mockito");
        list.add("PowerMock");
        assertThat(list.get(0), equalTo("Mockito"));
        assertThat(list.get(1), equalTo("PowerMock"));
        assertThat(list.isEmpty(), equalTo(false));

        when(list.isEmpty()).thenReturn(true);
        when(list.size()).thenReturn(0);

        assertThat(list.get(0), equalTo("Mockito"));
        assertThat(list.get(1), equalTo("PowerMock"));
        assertThat(list.isEmpty(), equalTo(true));
        assertThat(list.size(), equalTo(0));
    }

}

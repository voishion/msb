package com.meishubao.sample.mockito.lesson06;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * spy jdk17 中应用有问题
 *
 * @author lilu
 */
@RunWith(MockitoJUnitRunner.class)
public class SpyingTest {

    @Test
    public void testSpy() {
        List<String> realList = new ArrayList<>(10);
        List<String> list = spy(realList);

        when(list.add(any())).thenCallRealMethod();
        when(list.get(anyInt())).thenCallRealMethod();
        when(list.isEmpty()).thenCallRealMethod();

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

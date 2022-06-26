package com.meishubao.sample.mockito.lesson07;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author lilu
 */
public class ArgumentsMatcherTest {

    @Test
    public void basicTest() {
        List<Integer> list = mock(ArrayList.class);
        when(list.get(0)).thenReturn(100);
        assertThat(list.get(0), equalTo(100));
        assertThat(list.get(1), nullValue());
    }

    /* isA, any  */
    @Test
    public void testComplex() {
        Foo foo = mock(Foo.class);
        //when(foo.function(Mockito.isA(Parent.class))).thenReturn(100);
        when(foo.function(isA(Child1.class))).thenReturn(100);
        int result = foo.function(new Child1());
        assertThat(result, equalTo(100));

        result = foo.function(new Child2());
        assertThat(result, equalTo(0));

        reset(foo);

        when(foo.function(any(Child1.class))).thenReturn(100);
        result = foo.function(new Child2());
        assertThat(result, equalTo(0));
    }

    interface Parent {
        int work();
    }

    class Child1 implements Parent {
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }

    class Child2 implements Parent {
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }

    static class Foo {
        int function(Parent parent) {
            return parent.work();
        }
    }

}

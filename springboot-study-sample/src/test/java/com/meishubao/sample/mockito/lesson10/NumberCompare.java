package com.meishubao.sample.mockito.lesson10;

/**
 * @author lilu
 */
public interface NumberCompare<T extends Number> {

    boolean compare(T actual, T value);

}

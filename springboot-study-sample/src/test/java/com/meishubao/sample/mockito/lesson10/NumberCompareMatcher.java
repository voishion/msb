package com.meishubao.sample.mockito.lesson10;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * @author lilu
 */
public class NumberCompareMatcher<T extends Number> extends BaseMatcher<T> {

    private final T value;

    private final NumberCompare<T> numberCompare;

    public NumberCompareMatcher(T value, String action) {
        this.value = value;
        this.numberCompare = new DefaultNumberCompare<>(action);
    }

    @Override
    public boolean matches(Object actual) {
        return this.numberCompare.compare((T) actual, value);
    }

    public static <T extends Number> NumberCompareMatcher<T> gt(T value) {
        return new NumberCompareMatcher<>(value, "gt");
    }

    public static <T extends Number> NumberCompareMatcher<T> gte(T value) {
        return new NumberCompareMatcher<>(value, "gte");
    }

    public static <T extends Number> NumberCompareMatcher<T> lt(T value) {
        return new NumberCompareMatcher<>(value, "lt");
    }

    public static <T extends Number> NumberCompareMatcher<T> lte(T value) {
        return new NumberCompareMatcher<>(value, "lte");
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("compare e two number failed.");
    }

}

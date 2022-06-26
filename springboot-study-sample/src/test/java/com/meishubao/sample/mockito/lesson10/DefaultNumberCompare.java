package com.meishubao.sample.mockito.lesson10;

/**
 * @author lilu
 */
public class DefaultNumberCompare<T extends Number> implements NumberCompare<T> {

    private final String action;

    public DefaultNumberCompare(String action) {
        this.action = action;
    }

    @Override
    public boolean compare(T actual, T value) {
        Class<?> clazz = actual.getClass();
        if (Integer.class.equals(clazz)) {
            return compare((Integer) actual, (Integer) value);
        } else if (Long.class.equals(clazz)) {
            return compare((Long) actual, (Long) value);
        } else if (Float.class.equals(clazz)) {
            return compare((Float) actual, (Float) value);
        } else if (Double.class.equals(clazz)) {
            return compare((Double) actual, (Double) value);
        } else if (Short.class.equals(clazz)) {
            return compare((Short) actual, (Short) value);
        } else if (Byte.class.equals(clazz)) {
            return compare((Byte) actual, (Byte) value);
        } else {
            throw new AssertionError("The number type " + clazz + " not supported.");
        }
    }

    private boolean compare(Integer actual, Integer value) {
        boolean result = false;
        switch (this.action) {
            case "gt" -> result = actual > value;
            case "gte" -> result = actual >= value;
            case "lt" -> result = actual < value;
            case "lte" -> result = actual <= value;
            default -> {
            }
        }
        return result;
    }

    private boolean compare(Long actual, Long value) {
        boolean result = false;
        switch (this.action) {
            case "gt" -> result = actual > value;
            case "gte" -> result = actual >= value;
            case "lt" -> result = actual < value;
            case "lte" -> result = actual <= value;
            default -> {
            }
        }
        return result;
    }

    private boolean compare(Float actual, Float value) {
        boolean result = false;
        switch (this.action) {
            case "gt" -> result = actual > value;
            case "gte" -> result = actual >= value;
            case "lt" -> result = actual < value;
            case "lte" -> result = actual <= value;
            default -> {
            }
        }
        return result;
    }

    private boolean compare(Double actual, Double value) {
        boolean result = false;
        switch (this.action) {
            case "gt" -> result = actual > value;
            case "gte" -> result = actual >= value;
            case "lt" -> result = actual < value;
            case "lte" -> result = actual <= value;
            default -> {
            }
        }
        return result;
    }

    private boolean compare(Short actual, Short value) {
        boolean result = false;
        switch (this.action) {
            case "gt" -> result = actual > value;
            case "gte" -> result = actual >= value;
            case "lt" -> result = actual < value;
            case "lte" -> result = actual <= value;
            default -> {
            }
        }
        return result;
    }

    private boolean compare(Byte actual, Byte value) {
        boolean result = false;
        switch (this.action) {
            case "gt" -> result = actual > value;
            case "gte" -> result = actual >= value;
            case "lt" -> result = actual < value;
            case "lte" -> result = actual <= value;
            default -> {
            }
        }
        return result;
    }

}

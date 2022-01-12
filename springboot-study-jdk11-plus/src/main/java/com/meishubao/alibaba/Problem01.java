package com.meishubao.alibaba;

/**
 * 在三目运算符使用过程中，需要注意自动拆箱导致的NullPointerException
 *
 * @author lilu
 */
public class Problem01 {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = null;
        Boolean flag = false;
        // a*b的结果是int类型，那么c会强制拆箱成int类型，抛出NPE异常
        Integer result = (flag ? a * b : c);
        System.out.println(result);
    }

}

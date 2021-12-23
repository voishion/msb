package com.meishubao.study;

/**
 * String新增方法
 *
 * @author lilu
 * @since jdk11
 * @since jdk13
 */
public class StringTest {

    public static void main(String[] args) {
        jdk11();
        jdk13();
    }

    public static void jdk11() {
        // strip方法，可以去除首尾空格，与之前的trim的区别是还可以去除unicode编码的空白字符
        char c = '\u2000';//Unicdoe空白字符
        String str = c + "abc" + c;
        System.out.println(str.strip());
        System.out.println(str.trim());

        System.out.println(str.stripLeading());//去除前面的空格
        System.out.println(str.stripTrailing());//去除后面的空格

        //isBlank方法，判断字符串长度是否为0，或者是否是空格，制表符等其他空白字符
        String str1 = " ";
        System.out.println(str1.isBlank());

        //repeat方法，字符串重复的次数
        String str2 = "monkey";
        System.out.println(str2.repeat(4));
    }

    public static void jdk13() {
        // 在jdk13之前的版本中如果输入的字符串中有换行的话，需要添加换行符
        String s = "Hello\nWorld\nLearn\nJava";
        System.out.println(s);

        // jdk13之后可以直接这样写
        String s1 = """
           Hello
           World
           Learn
           Java
           """;
        System.out.println(s1);
    }
}

package com.meishubao.study;

import cn.hutool.core.util.StrUtil;

/**
 * 10进制、62进制互转
 *
 * @author lilu
 */
public final class Base62 {

    private static final int scale = 62;

    /**
     * 初始化 62 进制数据，索引位置代表字符的数值，比如 A代表10，z代表61等
     */
    private static final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 根据指定的62进制数获取下一个62进制数
     *
     * @param str 编码后的62进制字符串
     * @return 62进制字符串
     */
    public static synchronized String next(String str) {
        return encode(decode(str) + 1);
    }

    /**
     * 将数字转为62进制
     *
     * @param num Long 型数字
     * @return 62进制字符串
     */
    public static String encode(long num) {
        StringBuilder builder = new StringBuilder();
        int remainder;
        while (num > scale - 1) {
            // 对 scale 进行求余，然后将余数追加至 sb 中，由于是从末位开始追加的，因此最后需要反转（reverse）字符串
            remainder = Long.valueOf(num % scale).intValue();
            builder.append(chars.charAt(remainder));
            num = num / scale;
        }
        builder.append(chars.charAt(Long.valueOf(num).intValue()));
        return builder.reverse().toString();
    }

    /**
     * 62进制字符串转为数字
     *
     * @param str 编码后的62进制字符串
     * @return 解码后的 10 进制字符串
     */
    public static long decode(String str) {
        str = str.replace("^0*", "");
        long num = 0;
        int index;
        for (int i = 0; i < str.length(); i++) {
            index = chars.indexOf(str.charAt(i));
            num += (long) (index * (Math.pow(scale, str.length() - i - 1)));
        }
        return num;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String str = encode(2576460752303423487L);
        System.out.println("62进制：" + str);
        System.out.println("10进制：" + decode(str));
        System.out.println("10进制(下一个)：" + next(str));

        long num = 100000000L, count = 0;
        while (count < 100) {
            System.out.println(StrUtil.format("10进制:{}, 62进制:{}", num, encode(num)));
            num++;
            count++;
        }
    }

}

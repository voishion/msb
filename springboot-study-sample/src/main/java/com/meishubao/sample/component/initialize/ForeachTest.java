package com.meishubao.sample.component.initialize;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * arrayList.stream().forEach无法实现中止循环并退出
 * 解决办法：（使用arrayList.stream().anyMatch这个函数来进行遍历即可）
 * anyMatch()里接收一个返回值为boolean类型的表达式，只要返回true就会终止循环，这样可以将业务逻辑写在返回判断结果前
 *
 * @author lilu
 */
public class ForeachTest {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("a", "b", "c", "d");
        /*list.stream().forEach(x -> {
            if ("c".equals(x)) {
                // 中止退出
            }
            System.out.println(x);
        });*/
        list.stream().anyMatch(x -> {
            if ("c".equals(x)) {
                return true;
            }
            System.out.println(x);
            return false;
        });
    }

}

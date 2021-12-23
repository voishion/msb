package com.meishubao.study;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * jdk16的变化
 *
 * @author lilu
 * @since jdk16
 */
public class Jdk16ChangeTest {

    public static void main(String[] args) {
        //包装类构造方法的警告
        //使用包装类的构造方法在编译的时候会出现警告，不建议再使用包装类的构造方法。下面代码在javac编译之后会出现警告。
        Integer i = new Integer(8);

        //不建议使用包装类作为锁对象，倘若使用包装类作为锁对象，在编译时会出现警告。
        Integer x = 8;
        synchronized (x) {
            System.out.println(x);
        }

        //新增日时段
        //在DateTimeFormatter.ofPattern传入B可以获取现在时间对应的日时段，上午，下午等
        System.out.println(DateTimeFormatter.ofPattern("B").format(LocalDateTime.now()));

        //InvocationHandler新增方法
        invokeDefaultTest();
    }

    private static void invokeDefaultTest() {
        Girl girl = new Lucy();

        //不使用invokeDefault会调用重写的eat方法
        Girl proxy1 = (Girl) Proxy.newProxyInstance(girl.getClass().getClassLoader(), girl.getClass().getInterfaces(),
                (obj, method, params) -> {
                    Object invoke = method.invoke(girl);
                    return invoke;
                });
        proxy1.eat();

        //使用invokeDefault会调用父接口中的default方法
        Girl proxy2 = (Girl) Proxy.newProxyInstance(Girl.class.getClassLoader(), new Class<?>[]{Girl.class},
                (obj, method, params) -> {
                    if (method.isDefault()) {
                        return InvocationHandler.invokeDefault(obj, method, params);
                    }
                    return null;
                });
        proxy2.eat();
    }

    interface Girl {
        default void eat() {
            System.out.println("cucumber");
        }
    }

    public static class Lucy implements Girl {
        public void eat() {
            System.out.println("banana");
        }
    }

}

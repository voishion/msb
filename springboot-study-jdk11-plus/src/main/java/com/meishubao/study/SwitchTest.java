package com.meishubao.study;

/**
 * @author lilu
 *
 * @since jdk12
 * @since jdk13
 * @since jdk17
 */
public class SwitchTest {

    public static void main(String[] args) {
        //sinceJDK12();
        //sinceJDK13();
        sinceJDK17();
    }

    /**
     * @since jdk12
     */
    public static void sinceJDK12() {
        int month = 3;
        switch (month) {
            case 3,4,5 -> System.out.println("spring");
            case 6,7,8 -> System.out.println("summer");
            case 9,10,11 -> System.out.println("autumn");
            case 12, 1,2 -> System.out.println("winter");
            default -> System.out.println("wrong");
        }
    }

    /**
     * @since jdk13
     */
    public static void sinceJDK13() {
        int month = 3;
        String result = switch (month) {
            case 3,4,5 -> "spring";
            case 6,7,8 -> "summer";
            case 9,10,11 -> "autumn";
            case 12, 1,2 -> "winter";
            default -> "wrong";
        };
        System.out.println(result);
    }

    interface Animal{}

    static class Rabbit implements Animal{
        //特有的方法
        public void run(){
            System.out.println("run");
        }
    }

    static class Bird implements Animal{
        //特有的方法
        public void fly(){
            System.out.println("fly");
        }
    }

    /**
     * 在之前版本中新增的instanceof模式匹配的特性在switch中也支持了，即我们可以在switch中减少强转的操作
     *
     * @since jdk17
     */
    public static void sinceJDK17() {
        // Rabbit和Bird均实现了Animal接口
        // 新特性可以减少Animal强转操作代码的编写
        Animal a = new Rabbit();
        animalEat(a);
    }

    public static void animalEat(Animal a){
        switch(a){
            //如果a是Rabbit类型，则在强转之后赋值给r，然后再调用其特有的run方法
            case Rabbit r -> r.run();
            //如果a是Bird类型，则在强转之后赋值给b，然后调用其特有的fly方法
            case Bird b -> b.fly();
            //支持null的判断
            case null -> System.out.println("null");
            default -> System.out.println("no animal");
        }
    }

}

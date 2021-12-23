package com.meishubao.study;

/**
 * 友好的空指针（NullPointerException）提示，便于开发者快速定位空指针的对象
 *
 * @author lilu
 * @since jdk14
 */
public class NullPointerExceptionTipsTest {

    class Machine{
        public void start(){
            System.out.println("启动");
        }
    }

    class Engine{
        public Machine machine;
    }

    static class Car{
        public Engine engine;

    }

    public static void main(String[] args){
        //这里会报出空指针，但是哪个对象是null呢？
        new Car().engine.machine.start();
    }

}

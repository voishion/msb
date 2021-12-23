package com.meishubao.study;

/**
 * @author lilu
 */
public interface InterfaceTest {

    /**
     * 在jdk9中新增了接口私有方法，我们可以在接口中声明private修饰的方法了，这样的话，接口越来越像抽象类了
     */
    interface PersonHandler {
        //定义私有方法
        private void m1() {
            System.out.println("M1");
        }

        //default中调用
        default void m2() {
            m1();
        }

        void m3();

        static void m4() {
            System.out.println("M4");
        }
    }

    class ChinesePersonHandler implements PersonHandler {
        @Override
        public void m3() {
            m2();
        }
    }

    static void main(String[] args) {
        PersonHandler handler = new ChinesePersonHandler();
        handler.m3();
        PersonHandler.m4();
    }

}

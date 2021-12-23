package com.meishubao.study;

/**
 * 密封类和接口，作用是限制一个类可以由哪些子类继承或者实现<br>
 * <ol>
 *     <li>如果指定模块的话，sealed class和其子类必须在同一个模块下。如果没有指定模块，则需要在同一个包下。</li>
 *     <li>sealed class指定的子类必须直接继承该sealed class。</li>
 *     <li>sealed class的子类要用final修饰。</li>
 *     <li>sealed class的子类如果不想用final修饰的话，可以将子类声明为sealed class。</li>
 * </ol>
 *
 * @author lilu
 * @since jdk15
 */
public class SealedClassesTest {

    /**
     * Animal类，在指定允许继承的子类时可以使用全限定名
     */
    public static sealed class Animal
            permits Cat, Dog {//多个子类之间用,隔开

        public void eat() {
            System.out.println("123");
        }
    }

    public static final class Cat extends Animal {
        public void eat() {
            System.out.println("456");
        }
    }

    public sealed static class Dog extends Animal
            permits Husky {
    }

    public final class Husky extends Dog {
    }

    public static void main(String[] args){
        Cat c = new Cat();
        c.eat();
        Dog d = new Dog();
        d.eat();
    }

}

package com.meishubao.study;

/**
 * record类型<br>
 * 之前在编写javabean类的时候，需要编写成员变量，get方法，构造方法，toString方法，hashcode方法，equals方法。这些方法通常会通过开发
 * 工具来生成，在jdk14中新增了record类型，通过该类型可以省去这些代码的编写<br>
 * <br>
 * 记录类型有自动生成的成员，包括：
 * <ul>
 *     <li>状态描述中的每个组件都有对应的private final字段。</li>
 *     <li>状态描述中的每个组件都有对应的public访问方法。方法的名称与组件名称相同。</li>
 *     <li>一个包含全部组件的公开构造器，用来初始化对应组件。</li>
 *     <li>实现了equals()和hashCode()方法。equals()要求全部组件都必须相等。</li>
 *     <li>实现了toString()，输出全部组件的信息。</li>
 * </ul>
 *
 * @author lilu
 * @since jdk14
 */
public class RecordTypeTest {

    public record User(String name, Integer age) {

        public String display() {
            return name + age;
        }

    }

    public static void main(String[] args){
        User u = new User("jack",15);
        System.out.println(u);
        System.out.println(u.name());
        System.out.println(u.display());
        System.out.println(u.hashCode());
        System.out.println(u.toString());
    }

}

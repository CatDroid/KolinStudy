package com.ueno.Generics;

import java.util.ArrayList;
import java.util.List;


public class GenericsExtendSuper {

    static class Animal
    {
        public int id ;
        public Animal(int id)
        {
            this.id = id;
        }
    }

    static class Cat extends Animal
    {
        public boolean isMale ;
        public Cat(int id , boolean isMale) {
            super(id);
            this.isMale = isMale;
        }
    }

    static class LionCat extends Cat
    {
        public LionCat(int id , boolean isMale) {
            super(id, isMale);
        }
    }

    public void add(List<? extends Animal> l)
    {
        //l.add(new Animal(1));  add函数参数类型是 T
        //l.add(new Cat(2, false));
        // 不能add Cat/Animal
        //
        // 因为 List<? extends Animal> 只是声明传入的集合元素类型是Animal 但不确定是具体类型是哪个
        // 所以 不能修改传入的集合
        // 只能 获取集合的元素
        //
        // 也就是 List<? extends Animal> 的 Get 等成员函数返回的是 参数类型T (Animal)
        // 但是成员函数的输入参数 不能是 参数类型T 避免修改了原来的集合

        l.contains(new Cat(1,false)) ; // 所以contains函数参数是Object 而不是 T
    }

    /**

     那些你只能从中读取的对象为生产者(这个对象内部生成了数据,获取从其他地方offer给他了??)
     那些你只能写入的对象为消费者

     “为了灵活性最大化，在表示生产者或消费者的输入参数上使用通配符类型”，并提出了以下助记符：
     PECS 代表生产者-Extends、消费者-Super（Producer-Extends, Consumer-Super）

     */
    static public void useProducerData(List<? extends Animal> l)
    {
        // 使用一个生产者对象  在该对象上不允许调用 add() 或 set()。
        // 但这并不意味着该对象是不可变的：例如，没有什么阻止你调用 clear()从列表中删除所有项目，
        // 因为 clear() 根本无需任何参数
        //
        // 通配符（或其他类型的型变）保证的唯一的事情是类型安全

        while(l.size() != 0) {
            Animal a = l.remove(0); // 返回类型是  参数类型T
            System.out.println("use Producer Data, class = " + a.getClass().getSimpleName() + ", id = "+ a.id );
        }

    }

    // 调用者传入的是List<Cat> 或者List<base Cat> 所以调用者只能使用Cat及Cat Base的方法，在下面函数中往容器塞入Cat及其子类都是ok的
    //
    // List<? super Cat> 是个消费者 消费T 而且消费的是T或者T的子类
    // 传入的时候  List<? super Cat> 是个 Cat及其父类实例的容器
    static public void userConsumerData(List<? super Cat> l)
    {

        // 只能调用接受 Cat 作为参数的方法 （例如，你可以调用 add(Cat) 或者 set(int, Cat)）
        // 当然如果调用函数返回 List<T> 中的 T，你得到的并非一个 Cat 而是一个 Object

        // l.add(new Animal(1)); // 错误
        l.add(new Cat(123, false ));
        l.add(new Cat(24, true ));
        l.add(new Cat(345, false ));
        l.add(new LionCat(888, true ));

        // Cat d = l.get(0); // 错误
        Object c = l.get(0);

    }

    /**
        带 extends 限定（上界）的通配符类型使得类型是协变的（covariant）
        ? extends E 表示此方法接受 E 或者 E 的 一些子类型对象的集合，而不只是 E 自身
        这意味着我们可以安全地从其中（该集合中的元素是 E 的子类的实例）读取 E，但不能写入，因为我们不知道什么对象符合那个未知的 E 的子类型。
     */
    public static void main(String[] args) {

        List<Cat> mCat = new ArrayList();
        //mCat.add(new Cat(1, false));
        //mCat.add(new Cat(2, true));
        userConsumerData(mCat);

        System.out.println("before " + mCat.size() ); // 2
        useProducerData(mCat);
        // use Producer Data, class = Cat, id = 1
        // use Producer Data, class = Cat, id = 2
        System.out.println("after " + mCat.size() ); // 0
    }


    // 假设有一个泛型接口 Source<T>，该接口中不存在任何以 T 作为参数的方法，只是方法返回 T 类型值：
    static interface Source<T> {
        T nextT();
    }

    // 在 Source <Object> 类型的变量中存储 Source <String> 实例的引用是极为安全的——没有 消费者方法 可以调用。
    // 消费者方法 以T为参数的方法 比如 List<T>.add(T)  List<T> 就是个消费者 add就是消费的方法
    // 但是 Java 并不知道这一点，并且仍然禁止这样操作：
    void demo(Source<String> strs) {
        //Source<Object> objects = strs; // ！！！在 Java 中不允许

    }
    // 为了修正这一点，我们必须声明对象的类型为 Source<? extends Object>，这是毫无意义的，
    // 因为我们可以像以前一样在该对象上调用所有相同的方法，所以更复杂的类型并没有带来价值。但编译器并不知道。

    // 在 Kotlin 中，提供 out 修饰符 向编译器解释这种情况。 这称为"声明处型变"：
    // 我们可以标注 Source 的类型参数 T 来确保它仅从 Source<T> 成员中返回（生产），并从不被消费。
}

// Java 泛型 <? super T> 中 super 怎么 理解？与 extends 有何不同？
// https://www.zhihu.com/question/20400700
//
// 参数类型之间有继承关系，但是对应的泛型 就没有了继承的关系
//
// 就算容器里装的东西之间有继承关系，但容器之间是没有继承关系的
//
// 苹果 IS-A 水果
// 装苹果的盘子 NOT-IS-A 装水果的盘子
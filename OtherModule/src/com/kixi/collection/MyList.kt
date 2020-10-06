package com.kixi.collection

fun main(args: Array<String>) {

    // kotlin 分为不可变和可变集合  操作不可变集合会返回新的集合实例  所有只读的接口都是协变的


    // List只是一个interface  父接口是 Collection 再父是 Iterable
    var strings:List<String> = listOf("abc","def","ghi")
    println(strings.javaClass) // 这样知道是怎么class class java.util.Arrays$ArrayList (这个是泛型)

    // ArrayList 不可以增删元素 但可以修改  kotlin把这些修改的方法给删除了 从而使这个list不可变
    // 把kt的不可变的list 给到java 就变成可变了～


    //strings.set(1,"1234") // 不能调用set set已经被删除

    println("kotlin的集合可以使用 array[index] ${strings[0]} ");

    Utils.reset(strings);
    println("kotlin的集合在java中被修改 ${strings[0]} ");

    // class kotlin.collections.EmptyList 空集合 不能做任何操作  不对应java class
    println(emptyList<String>().javaClass);
    println(listOf<String>().javaClass); // class kotlin.collections.EmptyList

    try {
        val l = emptyList<String>()
        println(l[0])
    } catch (ex: IndexOutOfBoundsException) {
       ex.printStackTrace()
    }

    val l2 = listOfNotNull("abc", null, "dfe")
    println("listOfNotNull size ${l2.size}: ${l2}") // 不包含null


    val m2: MutableList<Int> = mutableListOf(1,2,3);
    m2[0] = 5; // 可以修改
    println("mutableListOf 可变集合 $m2 ${m2.javaClass}") // java.util.ArrayList 这个可以增加删除元素

    //  public actual typealias ArrayList<E> = java.util.ArrayList<E>
    val a2 = arrayListOf(1,2,3);
    a2[0] = 5
    println("arrayListOf 可变集合 $a2 ${a2.javaClass}")

    // class java.util.ArrayList        可以增删元素
    // class java.util.Arrays$ArrayList 不可以增删

    val ints: IntArray = intArrayOf(1,3,4); // java数组
    val intsKotlin: Array<Int> = arrayOf(1,3,4); // kotlin数组
    val l3:List<Int> = ints.toList(); // 数组转list
    println("IntArray to List  ${l3}")
}
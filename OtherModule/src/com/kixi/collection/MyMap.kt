package com.kixi.collection

private data class Car(val color:String, val model:String, val year:Int)

// Deconstruction 解构
// Destruct 析构
fun main(args: Array<String>) {

    val immutableMap = mapOf(
        1 to Car("red", "cat", 1992),
        2 to Car("green","dog", 1995),
        3 to Car("blue",  "tiger", 1881),
        4 to Car("yellow", "lion", 2010)
    ) // key to value

    // LinkedHashMap 迭代的顺序是可以预测的 kotlin需要这个可预测的顺序 以便把list转换为set和map
    println(immutableMap.javaClass) // class java.util.LinkedHashMap
    println(immutableMap)


    //hashMapOf<>()
    val mutableMap = mutableMapOf<Int, Car>()
    println(mutableMap.javaClass)   // class java.util.LinkedHashMap

    mutableMap.put(5, Car("orange", "panda", 1789))
    mutableMap += Pair(6, Car("grey", "bird", 2100));
    println(mutableMap)

    // 解构声明 把一个类的各个public属性 赋值给不同变量 对应的class必须为每个public属性实现component1～n函数
    val (firstValue, secondValue) = Pair(7, Car("brown", "elephant", 987))

    // data class 自动生成 component1 component2 component3等函数
    val (one,two,three) = mutableMap[5]!! // Car 解剖
    println("component1 ${one}; component2 ${two}; component3 ${three}")

    for ((key,value) in mutableMap) {
        println("key = ${key}, value = ${value}")
    }


}
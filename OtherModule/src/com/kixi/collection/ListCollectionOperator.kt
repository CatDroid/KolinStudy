package com.kixi.collection

import java.util.*

/*
倒序 reversed() 和 asReversed() 函数区别在于

reversed() 函数会返回一个新集合，后续对原始集合的改变不会影响到先前得到的倒序列表；

asReversed() 函数获取的是原始集合的一个反向视图，它会比 reversed() 函数更轻量，但后续对原始集合的改变都会放映在其反向视图中

 */
fun diff()
{
    val list = mutableListOf(0, 1, 2, 3, 4, 5)

    val asReversed = list.asReversed()
    val reversed   = list.reversed()

    println("Original list: $list ${list.hashCode()}")
    println("asReversed:    $asReversed  ${asReversed.hashCode()}")
    println("reversed:      $reversed ${reversed.hashCode()}")

    list[0] = 10

    println("Original list: $list")         // [10, 1, 2, 3, 4, 5]
    println("asReversed:    $asReversed")   //  [5, 4, 3, 2, 1, 10]
    println("reversed:      $reversed")

    if (asReversed == reversed) {
        println("equal ") // NO!
    }
}

fun <T> linkedListOf(vararg items: T)
        = LinkedList<T>().apply { for (i in items) { add(i) } }
        // apply 返回调用者本身 而不是lambada表达式的值


/*
数组arrayOf和列表listOf(以List及其子类型MutableList表示)有许多不同之处，其中最重要的是：

List和MutableList是具有不同实现的接口:ArrayList和LinkedList等 列表的内存表示和操作逻辑是在具体实现中定义的，例如LinkedList中的索引通过链接并花费O(n)时间，而ArrayList将其项动态存储在分配的数组。
Array是可变的(可以通过对它的任何引用进行更改)，但List没有修改方法(它不是MutableList的只读视图，也不是不可变的列表实现)
数组的大小是固定的，不能扩展或收缩保留标识(您需要复制一个数组来调整其大小)。在列表中，MutableList具有add和remove功能，可以增加和减少其大小

 */
fun main(args: Array<String>) {

    val mList = linkedListOf("a", "b", "c") // deprecated, not in standard
    println()

    val linkedList = LinkedList<String>(listOf("one", "two", "three")) // class java.util.LinkedList
    println("linkedList javaclass = ${linkedList.javaClass}")


    diff()

    val seasons = listOf("spring","summer","autumn","winter")
    val colors = listOf("red","green","yellow", "autumn", "autumn")

    println(seasons.last())
    println(seasons[0])
    println(seasons.first())
    println("getOrNull ${seasons.getOrNull(6)} ")
    println(seasons.max())

    val newStrings = seasons.reversed();
    println(newStrings)

    val n = seasons.asReversed() // 返回原始List的反向只读视图
    println("origin ${seasons.hashCode()} reversed ${newStrings.hashCode()} asReversed ${n.hashCode()}")


    val joinList = seasons + colors
    println("join/merge list ${joinList}" )

    val merge = seasons.union(colors) // 交集
    println("交集 ${merge}")

    val distinct = colors.distinct();
    println("去掉重复 ${distinct}")

    val pairs = seasons.zip(colors);
    println("pair of two list = ${pairs}")
    println("pair #0 ${pairs[0].first}, ${pairs[0].second}")



}
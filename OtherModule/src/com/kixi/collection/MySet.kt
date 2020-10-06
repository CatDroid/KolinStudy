package com.kixi.collection

// Set 跟 List 相似 但是不包含重复元素


fun main(args: Array<String>) {

    val set = setOf(1,2,3,3) // class java.util.LinkedHashSet
    println(set.javaClass)
    println(set.size)
    println(set) // 自动去重复

    println(set.plus(20)) // [1, 2, 3, 20] 返回的是新的set
    println(set) // [1, 2, 3]
    println(set.minus(2)) // [1, 3]
    println(set.minus(100)) // 不做任何操作 只返回新的set
    println(set.average()) // 2.0  // 平均值
    println(set.drop(2)) // [3] // 移除前两个


    var mset = mutableSetOf<Int>(1,2,3,3)
    println(mset.javaClass) // class java.util.LinkedHashSet
    mset.plus(20) // 没有改变原来的
    println("after plus ${mset}")
    println(mset.add(20))
    println("after add ${mset}")
    println(mset.remove(1))
    println("after remove ${mset}")
}
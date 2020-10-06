package com.kixi.collection

private data class Car1(val color:String, val model:String, val year:Int)

fun main(args: Array<String>) {

    val setInts = setOf(10, 1,2,3,4,5,6,7)
    val immutableMap = mapOf(
        3 to Car1("blue",  "tiger", 1881),
        4 to Car1("yellow", "lion", 2010),
        1 to Car1("red", "cat", 1992),
        2 to Car1("green","dog", 1995)
    )

    // filter 过滤 lambda作为条件 返回true的元素
    println ( setInts.filter { it % 2 == 0 } ) // 过滤剩下偶数

    // map 遍历集合中每个元素  返回包括lambda结果的list
    var newList = setInts.map { it + 100 } // 返回不是set
    println ( newList )  // 全部加100
    println ( setInts.map { it + 100 } .javaClass ) // class java.util.ArrayList


    // 把list通过foreach赋值给另外一个对象
    val mutableList = mutableListOf<Int>()
    newList.forEach { value -> mutableList.add(value + 100) }
    println(mutableList)

    // map 和 foreach 都可以完成遍历  map会保存每次lambda的结果到一个list中
    val result = setInts.map { mutableList.add(it + 200) }

    // all -- 全部元素都使lambda表达式返回true  不完整遍历--只有有一个元素执行lambda返回false就不会继续遍历
    val allTrue = setInts.all{ value -> println(value) ; value > 5}
    println("all are  ${allTrue}")

    // any -- 其中一个使lambda返回true,就停止遍历 并返回true
    val anyOneIsTrue = setInts.any{ it > 5}
    println("any One Is  ${anyOneIsTrue}")

    // count -- 满足lambda表达式的集合元素数量
    println("count   ${setInts.count { it > 5 }}")

    var found = setInts.find { it > 20 }
    println( found?.toString()?:"Not Found ")

    // array list set 都find /  map 没有find groupBy
    val mapValue = immutableMap.values
    println(mapValue.javaClass); // class java.util.LinkedHashMap$LinkedValues
    var found2 = mapValue.find { it.year > 1995 } // map没有顺序
    println( found2?.toString()?:"Not Found ")

    println(immutableMap.map{ it.value }.find { it.year > 2000 }) // map返回的是List<T>


    // 分组  结果是一个map, lambda表达式的所有可能取值为key 集合元素分组好的List为value
    val group1:Map<Boolean, List<Int>> = setInts.groupBy { value -> value > 5 }
    println("Group by boolean ${group1}" )

    val group2 = mapValue.groupBy { it.color }
    println("Group by color ${group2}")

    val sortResult =  mapValue.sortedBy { it.year }; // 返回List<Car>
    println("sortedBy year ${sortResult}")

    println("map is ${immutableMap}") // 按照定义的顺序 ??
    println("sort by map key ${immutableMap.toSortedMap()} ")

}
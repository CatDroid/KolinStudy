package com.yori.Logic

class MyRange {

}

fun main(args: Array<String>) {

    // 区间类型
    val intRange = 1..5 ; // 类型是IntRange
    val charRange = 'a'..'z' // CharRange

//    var i: Int = 0;
//    for ( ; i < 10 ; i++) kotlin 没有这种循环



    // in 为操作符 判断 i是否在range里面 在的话整个表达式的值为true
    // range必须是可以迭代的
    for (i in IntRange(1, 4)) {
        println(i)
    }

    val intDownToRange = 5 downTo 1 // IntProgression类型
    val intReverseRange = intRange.reversed();

    for (i in intDownToRange) {
        println("down to (IntProgression) ${i}")
    }
    println("---------")

    val asRange = 1..10 step 2
    val newRange = intRange.step(2);// 原来是IntRange step返回是 IntProgerssion
//    for (i in intRange) { // 不改变原来的IntRange
//        println(i);
//    }
    for (i in newRange) {
        println(i);
    }

    println("*********")
    for (i in 1..10 step 2) {
        println(i)
    }

    println("^^^^^^^^^")
    for (index in 0 until 10) { // 不包含10 [0,10) until 不包含结束
        println(index )
    }

    val temp = arrayOf("Cat","Dog","Panda","Tiger")
    for(index in 0 until temp.size) {
        println("until arrayof size ${temp[index]}")
    }

    //   var23 = (new IntRange(5, 10)).contains(3);
    println( 3 in IntRange(5,10)) // false

    println( 3 !in 5..10) // true

}
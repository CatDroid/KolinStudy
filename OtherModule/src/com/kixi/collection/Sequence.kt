package com.kixi.collection



fun main(args: Array<String>) {


    var nameList:List<String> = listOf("Joe","Mary","John","Smith","Joana")

    // 产生中间集合的 链式函数调用  消耗资源
    var result = nameList.map { println("map $it"); it.toUpperCase() }
        .filter{ println("filter $it"); it[0] == 'J'}
        .find { println("find $it");  it.endsWith('N') }


    println(result?.toString()?:"no found");


    println("使用sequence做链式函数调用")

    // Sequence 跟 Stream(Java 8) 一样, Android不支持
    //
    // Sequence是个接口，只有一个函数 iterator 用来逐个获取Sequence的每个元素(Entry) 可以看作是元素的Stream
    //
    // Sequence 避免产生很多的中间集合
    //
    // 中间类型操作 返回的还是Sequence
    // 终结类型操作 返回的不是Sequence 而是终止链式函数调用  Sequence的中间类型调用是懒惰的 没有终止操作 不会执行任何中间类型操作

    var seq:Sequence<String> = nameList.asSequence();
    println(seq.javaClass) // class kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1


    // 这个map函数返回的不是lambda表达式的结果组成的List,而是Sequence
    var s1:Sequence<String> = seq.map { println("map $it"); it.toUpperCase() }
    println(s1.javaClass) // class kotlin.sequences.TransformingSequence


    seq.map { println("map $it"); it.toUpperCase() }
        .filter{ println("filter $it"); it[0] == 'J'}
        .find { println("find $it");  it.endsWith('N') }


    println("没有终结类型操作 中间类型操作不会执行  中间类型操作是懒惰的")
    seq.map { println("map $it"); it.toUpperCase() }
        .filter{ println("filter $it"); it[0] == 'J'}


}
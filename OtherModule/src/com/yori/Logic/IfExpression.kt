package com.yori.Logic


// kotlin 分 if statement 和 if expression
// if statement 跟 java的if一样
// if expression 就是整个if返回一个结果


fun main(args: Array<String>) {


    val temp:Boolean = true ;

    val str = if (temp) {
        "True"
    } else {
        "False"
    }
    println(str);

    val id = if (temp) 20 else 30 ;  // 这个可以完成 ? : 三元运算符的功能



    var nullable:String? = null ;
    println(nullable?.toString()?:"default"); // ?: 只要前面为null 就会返回后面的值

}
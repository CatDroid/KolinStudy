@file: JvmName ("StaticSchool") // 注解 告诉JVM 替换默认的top-level静态类的名字
package com.coco.CallKotlinFromJava.kotlin


// Kotlin会产生一个静态类 来包含这些top-level function 默认静态类名字是 "文件名字kt"

fun myTopLevelFunction(arg1:Int, arg2:String):Boolean
{
    println("arg1 ${arg1}, arg2 ${arg2}");
    return true ;
}

// 扩展函数
fun String.print() = println(this)
// -- > fun print(String arg1) !
// -- > class StaticSchool { static fun print(String arg1) ! }
// 所以在java中 需要把 receiver 作为第一个参数
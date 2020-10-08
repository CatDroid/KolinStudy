@file: JvmName ("StaticSchool") // 注解 告诉JVM 替换默认的top-level静态类的名字
package com.coco.CallKotlinFromJava.kotlin

import java.io.IOException


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

// 不同的包路径
// kotlin --- import com.coco.CallKotlinFromJava.kotlin.SCHOOL_DEPRECATED
// java   --- import com.coco.CallKotlinFromJava.kotlin.StaticSchool.SCHOOL_DEPRECATED

const val SCHOOL_DEPRECATED: String = "deprecated"


class MySchool(val name:String, var isPublic:Boolean, @JvmField val year:Int)
{
    companion object {

        fun kotlinStaticMethod(name:String):Boolean
        {
            println("kotlinStaticMethod called ${name}")
            return true
        }

        //@JvmField
        val IS_KOTLIN:Boolean = true ;

        const val IS_JAVA:Boolean = false ;


        // 加上JvmStatic注解 会产生两个版本
        // 一个是带有companion field的函数
        // 一个是没有带companion的 静态函数
        @JvmStatic
        fun kotlinStaticMethod_JVMStatic(name:String):Boolean
        {
            println("kotlinStaticMethod_JVMStatic called ${name}")
            return true
        }
    }
}

object SingleOne{

    // @JvmStatic 也是产生两个版本
    fun doSomeThing() {
        println("SingleOne  doSomeThing")
    }

    fun nonNullableArg(arg:String) { // 参数类型是不可为null 运行时 在java调用传入null会出现异常
        println(arg); // 编译成bytecode会 检查参数 是否为null
        // java.lang.IllegalArgumentException: Parameter specified as non-null is null:
    }

    // java: exception java.io.IOException is never thrown in body of corresponding try statement
    @Throws(IOException::class) // 注释这个函数 会告诉编译器抛出异常
    fun throwIoException() {
        throw IOException("throwIoException")
    }

    // kotlin编译器只为JVM生成一个版本 也即是带有两个参数的函数(签名)
    @JvmOverloads // 把这个函数每一可能的版本都产生出来
    fun defaultArg(name:String, id:Int = 12) {
        println("defaultArg name = ${name} , id = ${id}(default 12) ")
    }
}

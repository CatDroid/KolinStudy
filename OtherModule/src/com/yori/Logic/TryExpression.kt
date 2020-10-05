package com.yori.Logic

import java.lang.Exception
import java.lang.NumberFormatException
import java.lang.RuntimeException

fun getNumber(string:String):Int { // kotlin 不区分uncheck和check expction 不需要在函数声明excption
    return try {
        Integer.parseInt(string)
    } catch (ex:NumberFormatException) {
        println("can not format string to int")
        if (string == "any") {
            throw RuntimeException("any") // 这个直接抛出异常 Exception in thread "main" java.lang.RuntimeException: any
        }
        0
    } finally {
        20 // 不会用这个的值
    }
}

fun main(args: Array<String>) {


    println(getNumber("123")) // 123
    println(getNumber("1.23")) // 0
    println(getNumber("any"))
}
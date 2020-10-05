package com.yori.Logic

fun main(args: Array<String>) {

    var num = 300
    val cal = 200

    when (num) {
        100 -> println("100")
        200 -> { // 执行多条语句
            val temp:String = "Hello"
            println("$temp -> 200")
        }
        300 -> println("300")
        400,500 -> { // 多个分支执行相同代码
            println("branch 400,500 match is ${num}")
        }
        in 500..600 -> {
            println("branch range [500,600]")
        }
        cal + 100 -> { // 任何表达式都可以作为分支条件 前面如果满足了就不会走这里
            println("branch ${cal + 100}")
        }
        else -> println("do not match")
    }

    val obj1:Any = "This is a string"
    val obj2:Any = true
    val obj3:Any = 55

    val something :Any = obj3

    when (something) { // 可以实现 Auto-casting
        is String -> println("${something} is string")
        is Boolean -> println("${something} is boolean")
        is Int -> println("${something} is Int")
    }
    // 分支条件 可以是 字面量  表达式 is

    // when表达式 必须 覆盖所有的可能  比如有else分支或者使用class enum
    val typeName =   when (something) { // 可以实现 Auto-casting
        is String -> "string"
        is Boolean -> "boolean"
        is Int -> "int"
        else -> "unknown"
    }
    println("something is ${typeName}")


    val num2 = 30;
    val num3 = 50;

    when { // 省略参数/无自变量的when
        num2 > num3 -> println("greator")
        num2 < num3 -> println("less")
        else -> println("equal")
    }

}
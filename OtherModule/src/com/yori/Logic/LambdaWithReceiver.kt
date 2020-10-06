package com.yori.Logic

import java.lang.StringBuilder


class HTML private constructor(val start:Int){

    companion object {
        fun html(value:Int, init: HTML.() -> Unit): HTML{
            val html = HTML(value)
            html.init()
            return html;
        }
    }

    fun call() = println("call Class HTML instance function ")

    override fun toString(): String {
        return "HTML $start"
    }
}

fun countTo20_1():String
{
    var sb = StringBuilder()
    for (i in 1..19) {
        sb.append(i)
        sb.append(", ")
    }
    sb.append(20)

    return sb.toString();
}

fun countTo20():String
{
    // with 函数的定义
    // public inline fun <T, R> with(receiver: T, block: T.() -> R): R
    // block是带接受者类型的函数类型 接受者类型 和第一个参数receive一样T
    // with的返回值类型就是lambda的返回类型

    var sb = StringBuilder()

    with(sb,
        {
            for (i in 1..19) {
                append(i)
                append(", ")
            }
            append(20) // append 返回 StringBuffer本身
        })

    return sb.toString();
}


// 最简形式
fun countTo20_2() = with(StringBuilder()) {
        for (i in 1..19) {
            append(i)
            append(", ")
        }
        append(20)
        toString() // StringBuffer.toString
    }


// apply 返回的是StringBuffer
// public inline fun <T> T.apply(block: T.() -> Unit): T
// block的函数类型是  T.() -> Unit 不用返回   T就是 StringBuffer
fun countTo20_3() = StringBuffer().apply(){
    for (i in 1..19) {
        append(i)
        append(", ")
    }
    append(20)
    toString()
}

fun findByName(employees:List<Employee>, name:String)
{
    employees.forEach {
        if (it.name == name) {
            println("$name In List")
            return // 非局部返回  不只是从forEach函数中返回 还从findByName返回
        } // 非局部返回 前提条件是接受lambda的函数 必须是inline函数  forEach是inline
    }
    println("$name Not in List")
}


fun findByName2(employees:List<Employee>, name:String)
{
    employees.forEach {
        if (it.name == name) {
            println("$name In List")
            return@forEach   // 只是从foreach中返回 需要用label实现
        } // @forEach是编译器默认的label  隐式标签---该标签与接受该 lambda 的函数同名
    } // 标签的格式为标识符后跟 @ 符号，例如：abc@、fooBar@都是有效的标签
    // https://www.kotlincn.net/docs/reference/returns.html
    println("$name Not in List")
}


fun main(args: Array<String>) {

    /*
        在kotlin中，提供了指定的接受者对象调用Lambda表达式的功能
        在"函数常量"的函数体中，可以不使用任何额外限定符，调用接收者的方法，这与扩展函数相似允许访问函数体内部接收者的成员

        函数类型
        (T, P) -> R  // 表示接收一个T类型和P类型的参数，返回一个R类型值的Lambda表达式类型

        A.(B) -> C  // 函数类型可以有一个额外的接收者类型，它在表示法中的点之前指定： 类型 A.(B) -> C 表示可以在 A 的接收者对象上以一个 B 类型参数来调用并返回一个 C 类型值的函数

     */
    val func: HTML.() -> Unit = { ->
        println("带有接收者的函数常量")
        call(); // 调用接受对象的成员方法 无需限定符
    };

    val obj = HTML.html(13, func );

    println(obj)

    // 匿名函数作为接收者类型
    val iop = fun Int.( other : Int) : Int = this + other
    println(2.iop(3))

    val iop2: Int.(Int) -> Int = { other:Int -> this + other } // 这个需要声明iop2的类型

    println(2.iop2(3))


    println(countTo20());

    println(countTo20_3().toString())


    val employees = listOf(Employee("aaa",1995),
        Employee("bbb", 1987),
        Employee("ccc", 1990)
    )
    findByName(employees, "ccc");

}
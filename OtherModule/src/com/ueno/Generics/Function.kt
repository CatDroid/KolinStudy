package com.ueno.Generics

import java.lang.StringBuilder
import kotlin.math.min

// 类型参数声明  T是个通用类型
fun <T> printList(list: List<T>)
{
    list.forEach { println(it) }
}

// 用扩展函数的方式
fun <T> List<T>.printList2()
{
    forEach { println(it) }
}

// 两个会有同样的JVM函数签名 JVM signature (printList(Ljava/util/List;)V):

//Error:(4, 1) Kotlin: Platform declaration clash: The following declarations have the same JVM signature (printList(Ljava/util/List;)V):
//fun <T> printList(list: List<T>): Unit defined in com.ueno.Generics in file Function.kt
//fun <T> List<T>.printList(): Unit defined in com.ueno.Generics in file Function.kt

// https://my.oschina.net/u/4266094/blog/4172416
// Kotlin注解  @JvmName
// 注意这个扩展函数的函数签名是 printList(Ljava/util/List;)V)
// 在字节码文件中将会将被扩展的类型 作为 方法的参数
//fun <T> List<T>.printList()   << 冲突 fun <T> printList(list: List<T>)
//{
//    forEach { println(it) }
//}
@JvmName("printListJvm")  // << 这个注解 只是给JVM使用 对于kotlin没有作用
fun <T> List<T>.printList()
{
    forEach { println(it) }
}



// 类型参数声明 加上限声明
fun <T:Number> List<T>.convertToInt():List<Int>
{
    return map { it.toInt() }
}


// 多个上限条件 使用where
fun <T> T.AppendString(other:T)
where T: CharSequence, T:Appendable
{
     println(append(other).toString())
}

fun main(args: Array<String>) {

    val temp = listOf("blue","orange", "brown")
    printList<String>(temp);
    temp.printList2()

    // T 包括 nullable type
    val ints:List<Int?> = listOf(1,2,3,4,null,5)
    ints.printList() // 调用的是 @JvmName("printListJvm") fun <T> List<T>.printList()

    val mints = mutableListOf(1,2,3,4)
    mints.printList2();


    val floats:List<Float> = listOf(1.2f, 3.333f, 4.111f) // 浮点数字面量默认是double类型
    println(floats.convertToInt())


    StringBuilder("hello1").AppendString(StringBuilder("world"));

}
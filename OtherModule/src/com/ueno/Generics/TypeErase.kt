package com.ueno.Generics

// 内联函数与具体化的类型参数
// inline function body 不是编译成function code
// inline + reified 类型参数具体化 不会导致类型在运行期被类型擦除
// 不是所有的方法都适合声明成inline 编译器会给出comment告知是否需要inline
// JVM也会优化function是否inline
// inline 1.函数需要lambda作为参数(return返回被调用函数)，2.参数是泛型并且是reified
inline fun <reified T> getElementByType(list:List<Any>):List<T>
{
    val newList:MutableList<T> = mutableListOf<T>();
    list.forEach {
        if (it is T) { // 如果不是reified 这里 is 将会出错 因为T是泛型 会类型擦除了
            newList.add(it)
        }
    }

    return newList
}

// expected performance impact of inline  insignificant , inlining works best for functions with parameters of functional types
// 内联的预期性能影响 微不足道  内联最适合有函数类型参数
inline fun get(temp:Int, temp1:Int):Int
{
    return temp + temp1
}

// JVM 没有泛型的概念
// 泛型只是编译期的feature 不是运行期的
fun main(args: Array<String>) {


    val temp = listOf("a","b","c")

    // 和java一样 由于类型擦除 不能使用instanceof 泛型
    // Cannot check for instance of erased type: List<Boolean>
    // println(temp is List<Boolean>)
    println(temp is List<String>) // 总是true 被编译器优化成true了

    var listAny:Any = listOf("a", "b" , "c")
    if (listAny is List<*>) { // 不能写List<Int> 只能用<*> 来区分是map list还是set
        println("This is list container")

        // java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Number
        // 编译期没有问题 运行异常
        try {
            val one = listAny as List<Int>
            one[0].plus(12);
        } catch (ex:ClassCastException) {
            ex.printStackTrace()
        }
    }

    when(listAny) {
        is List<*> -> println("This is list container")
        is Set<*> -> println("This is set container")
        is Map<*,*> -> println("This is map container")
        else -> println("unknown container ")
    }

    val any = listOf("string", 1.22f, 3.45, 12 );
    println( getElementByType<Double>(any) ); // [3.45]
}
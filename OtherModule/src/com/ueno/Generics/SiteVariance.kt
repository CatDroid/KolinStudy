package com.ueno.Generics



open class Car
class Ford: Car()

// 使用处型变：类型投影
fun <T:Car> copyCars(source:MutableList<out T>, destination:MutableList<in T>)
{
    source.forEach { destination.add(it) }
}


fun main(args: Array<String>) {

    var cars = mutableListOf(Car(), Car())
    var fords = mutableListOf(Ford(),Ford(),Ford())

    copyCars(fords, cars);

}
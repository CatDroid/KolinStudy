package com.may.MyInterface

// interface 默认 open
interface IFirstInterface {
// 接口没有构造函数
    fun SetCallback(index:Int):String
}

interface SubInterface : IFirstInterface {
    fun SetProxy(number:Double):String
    val number: Int;            // 抽象属性
    val number2:Int
        get() = number * 2 ;    // 非抽象属性 但依旧是open的 可以被重写  不能使用field
}


// 抽象的属性的override 可以在构造函数中
class OneClass(override val number: Int) : SubInterface {

    override fun SetCallback(index: Int): String = "${index}"

    override fun SetProxy(number: Double): String {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return "${number}"
    }

    override val number2:Int
        get() = number * 3 ;

}
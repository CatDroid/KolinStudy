package com.hozon.MyNewPackage

// 默认是public final
// Any Class是所有类的基类 -- Java Object
// toString hashCode equal
open class BaseClass {

}

open abstract class NewBaseClass(val modeName:String)
{

    fun printModel() { // final public的函数  子类不能改写
        println("The Base model is ${this.modeName}")
    }

    open fun printModel2() {
        println("2 The Base model is ${this.modeName}")
    }

    abstract fun callName():Double
}


class Cat():NewBaseClass("Cat")
{
//    override fun printModel()  {
//        println("The Base model is ${this.modeName}")
//    }
    override fun printModel2() {
        println("override open function is called , ${this.modeName}")
    }

    override fun callName():Double {
        println("override abstract is called,  ${this.modeName}")
        return 1.0
    }

}


class SubClass():BaseClass() {

}


class SubClass2:BaseClass {

    // 没有主构造函数 就要定义次构造函数 并使用super调用父类的构造函数
    constructor():super()
    {

    }
}
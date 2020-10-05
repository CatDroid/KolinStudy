package com.Fela.MyObject

import java.time.Year

class MemberClass
{
    init {
        println("BaseClass Member's Class init block is called");
    }
}

open class BaseClass(val id:Int = 11)
{
    // 按顺序调用 init 1 -> member - > init 2
    init {
        println("MySingleton BaseClass init block 1 is called")
    }
    val member:MemberClass = MemberClass() ;

    init {
        println("MySingleton BaseClass init block 2 is called")
    }

}
// Object关键字 用在 -- singleton , companion object , object expression
// object declaration 是线程安全 不用跟java单例那样在初始化函数加锁
// 不能在函数中定义object declaration,也就是没有局部的object declaration
object MySingleton:BaseClass(20) {

    // object MySingleton (var name:String, val id:Int)
    // {
    //
    // }
    //
    // constructor()
    // {
    //
    // }

    // object是不能有构造函数

    init {
        // 首次使用会创建实例并调用init代码块
        println("MySingleton init !!")
    }

    val currentYear = Year.now().value

    fun getTag() = "This is an example";
    fun getCopyRight() = "Copyright ${currentYear}, all rights reserved"


}
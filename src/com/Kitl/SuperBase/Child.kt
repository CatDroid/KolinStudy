package com.Kitl.SuperBase

import com.Kitl.SuperBase.Base as Base1

class Child(): Base1(12) { // 子类主构造函数初始化时会调用 调用父类的参构造函数(任何一个都可以)

    constructor(name:String):this()
    {

    }

    constructor(name:String, isMan:Boolean):this()
    {

    }
}


class Child2: Base1 { // 子类没有主构造函数 那么次构造函数 就必须调用super父类的构造函数

    // 由于是通过次构造函数调用父类的带参构造函数，所以 Base1 后的括号()就省略了

    constructor(name:String):super(13)
    {

    }
}

fun main(args:Array<String>)
{
    println(Child().id);
    println(Child2("default").id);
}
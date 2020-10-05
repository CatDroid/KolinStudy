package com.hozon.MyNewPackage


// 没有幕后字段(backing field):不管是扩展函数,还是扩展属性,都没有将扩展的成员加入到类中,扩展属性没有幕后字段
// 不能有属性初始化器:扩展属性由于没有幕后字段,因此不能定义属性的初始化器,给扩展属性定义初始化器有如下报错信息
// Extension property cannot be initialized because it has no backing field


// 扩展属性 不在类的bytefield
//  var 修饰扩展属性, 必须定义 get/set 属性访问器方法
//  val 修饰扩展属性, 必须定义 get 方法 , 必须不能定义set方法
val String.lastOne: Int // 不能有初始化值
    get() = length - 1  // 不能使用field

// 扩展函数 好像这个函数加入了String类中 可以使用 this (receive object)或者 直接访问String的成员函数
// String 是receiver type
// 扩展函数 一般只声明在top-level\
// 但是扩展函数并不允许你打破封装。跟定义在类中方法不同，它不能访问那些私有的、受保护的方法和属性。
fun String.upper3FirstAndLast3():String // 调用这个函数的object是receive object
{
    //val upper3first = arg.substring(0,3).toUpperCase() + arg.substring(3)
    val upper3first = this.substring(0,3).toUpperCase() + substring(3) // 不加this
    return upper3first.substring(0, upper3first.length - 3) + upper3first.substring(upper3first.length -3 ).toUpperCase()
}

/*
    Kotlin 类中不能直接声明 Fields。
    然而，当一个属性需要一个 backing field 时，Kotlin 会自动地提供它。
    在访问器中使用 field 标识符就可以引用到 backing field

    var counter = 0 // Note: the initializer assigns the backing field directly
    set(value) {
        if (value >= 0) field = value
    }

    初始化值 只能在有backing-field的情况下才能赋值

    val isAdult
    get() = this.age > 18 // 每次我们获取 isAdult 属性，都会进行计算，它本身并不需要字段来保存它的值


    从有没有 backing field 的角度，
    可以 Kotlin 的属性分为两类：有 backing field 的属性，没有 backing field 的属性。

        有 backing field 的属性会把值以 Java 中字段的形式存储起来。这个字段就把值存储在内存里了。
        没有 backing field 的属性不能把值以 Java 中字段的形式存储起来。它必须每次调用时都通过计算得到它的值。

    Kotlin 中的属性与 Java 中的字段相比较，是一个更高层次的概念


    另外 有 backing property 可以用来实现惰性初始化 以及 一个属性，对外表现为只读，对内表现为可读可写
    https://blog.csdn.net/willway_wang/article/details/100184784

*/
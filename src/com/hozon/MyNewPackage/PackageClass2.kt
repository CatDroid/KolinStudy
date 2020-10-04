package com.hozon.MyNewPackage

// 主构造函数 和 副构造函数
// 类名和{ 之间是类头 包括 可见修饰符 和 主构造函数 internal constructor 可以删除
// 可以在主构造函数的参数列表 声明成员变量 加上val和var ; 主构造函数不能有函数体
class PackageClass2 internal constructor(
    val name:String,
    var age:Int = 23, // 参数默认值
    gender:String,
    isMarried:Boolean) {

    val gender:String // 没有初始化

    init { // 初始化代码块  是主构造函数的一块  可以多个 没有定义主构造函数也会调用
        this.gender = gender ;
        println("this is init block")
    }

    var mIsMarried = isMarried; // 属性的初始化 主构造函数会按照顺序
  /*
  PackageClass2(@NotNull String name, int age) {
      self.name = name ;
      self.age = age ;
  }
   */


    var mPrivateObj = PackageClass();

    // 次构造函数 必须 在首行 (直接或间接)调用主构造函数
    // 参数列表不能有val或者var
    constructor(name:String, age:Int, gender:String):this(name,age,"n/a",false)
    {
        println("this is 1st secondary constructor ")
    }

    constructor(name:String, age:Int):this(name, age,"n/a") // 这个是调用 上面的构造函数 再调用主构建函数
    {
        // 执行顺序
        // this is init block
        // this is 1st secondary constructor
        // this is 2nd secondary constructor

        println("this is 2nd secondary constructor ")
    }


    fun PackageClass2()
    {
        mPrivateObj.dumpPackageClass();
    }

    class NoBodyClass // 不需要{}
}
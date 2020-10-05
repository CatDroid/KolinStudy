package com.hozon.MyNewPackage

// 主构造函数 和 副构造函数
// 类名和{ 之间是类头 包括 可见修饰符 和 主构造函数 internal constructor 可以删除
// 可以在主构造函数的参数列表 声明成员变量 加上val和var ; 主构造函数不能有函数体
class PackageClass2 internal constructor(
    val name:String,
    var age:Int = 23, // 参数默认值
    gender:String,
    isMarried:Boolean) {

    var gender:String = ""// 属性必须被初始化
    private set(value) {
        println("this is customize SET ")
        field = value // 不能够使用gender 会产生递归调用
        // field的用法, field被大神们翻译成Backing Fields(后端变量)
        // 在set/get里是不能有这个变量的), 因为属性的调用也涉及到了set/get会造成递归调用,
        // 所以要解决引用自身的问题, kotlin发明了field(后端变量)来解决这个问题
    }
    get() {
        println("this is customize GET ")
        //return field
        return "" // 没有使用field
    }


    init { // 初始化代码块  是主构造函数的一块  可以多个 没有定义主构造函数也会调用
        this.gender = gender ;
        println("this is init block")
    }

    private var mIsMarried = isMarried; // 属性的初始化 主构造函数会按照顺序
    /*
      PackageClass2(@NotNull String name, int age) {
          self.name = name ;
          self.age = age ;
      }
    */
    // 如果属性的可见性是public 默认生成的set和get方法 都属性的可见访问一样
    // 但是set函数的可见访问 可以往更小的方向 比如public属性->get方法可以是internal private
    // 外部任何对属性的范围（对象.成员）都是调用set和get方法


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

    // 函数体只有一行的 可以直接 = 返回结果
    fun OneLineFunction():String
            = "this is one line function : ${name}, ${age}, ${gender}, ${mIsMarried}"

    class NoBodyClass // 不需要{}
}
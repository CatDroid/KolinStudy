package com.dali.EnumClass

enum class Department(val fullName:String, val id:Int){
    // 每个枚举量都是 枚举类的实例 所以需要给参数构造
    HR("hr",1) {
        override fun getSomething():String {
             return "${fullName}--${id}"
        }
    },
    IT("it",2) {
        override fun getSomething(): String {
            return "${fullName}**${id}"
        }
    },
    SALES("sales", 3){
        override fun getSomething(): String {
            return "${fullName}>> ${id}"
        }
    }

    ; // 分号结束

    fun dumpInfo() = println("The ${fullName} enum id is ${id}")

    abstract fun getSomething():String;
}


fun main(args: Array<String>) {

    // <init> is private  在enum class 构造函数是private的
    //val d:Department = Department("1",2);
    val d:Department = Department.HR;
    // enum 默认有两个属性 val name:String 和  val ordinal: Int
    println(d.name);    // 枚举的名字  HR
    println(d.ordinal); // 枚举的顺序 0
    d.dumpInfo()
    println(Department.IT.getSomething());
    println(Department.SALES.getSomething());
    val temp:Array<Department> = Department.values()// 列举所有定义的枚举常量
    for (one in temp){
        println("enum ${one.name},${one.ordinal} --> ${one.fullName},${one.id}")
    }
    /*
        enum HR,0 --> hr,1
        enum IT,1 --> it,2
        enum SALES,2 --> sales,3
    * */

    println(Department.values().joinToString { it.name })
}
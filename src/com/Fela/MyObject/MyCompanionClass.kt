package com.Fela.MyObject

class MyCompanionClass {

    val oneString:String

    // 如果没有定义主构造函数，次构造函数不用显式调用主构造函数
    private constructor(str: String)
    {
        oneString = str ;
    }

    private constructor(str: String, lowerCase:Boolean)
    {
        if (lowerCase) {
            oneString = str.toLowerCase();
        } else {
            oneString = str.toUpperCase();
        }
    }

    interface CreateInterface {
        fun Create():MyCompanionClass;
    }

    open class CompanionBaseClass(val id:Int = 111)

    override fun toString(): String {
        return oneString.toString()
    }

    // Tool -- kotlin -- show bytecode 可以看到反编译后到java的结果

    // kotlin 没有 static关键字
    // 伴生对象 类似static 可以不定义限定符
    companion object CONSTANT : CreateInterface, CompanionBaseClass(112){ // 伴生对象可以实现接口和继承类

        override fun Create(): MyCompanionClass {
            return MyCompanionClass("companion object implement interface ", false);
        }

        private val privateVar = 10
        public fun accessPrivateVar() = "Accessing privateVar: ${privateVar}"

        // 加上这个注解 在JVM层才能看到跟java一样的 会在CompanionBaseClass类中生成一个static的factory函数(实际还是调用CONSTANT类factory方法)，
        // 否则是CONSTANT类的factory方法
        //@JvmStatic
        public fun factory(str:String, lowerCase: Boolean):MyCompanionClass {
            return MyCompanionClass(str, lowerCase);
        }
    }
}

fun main(args: Array<String>)
{
    //println(MyCompanionClass.CONSTANT.accessPrivateVar())
    println(MyCompanionClass.accessPrivateVar()) // 可以不使用限定符

    println(MyCompanionClass.factory("Companion Object Factory!", true))

    println(MyCompanionClass.Create())

    println(MyCompanionClass.id)
}
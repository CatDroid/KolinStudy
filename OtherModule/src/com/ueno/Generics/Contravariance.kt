package com.ueno.Generics

open class Flower2(val name:String)

class Rose2 : Flower2("Rose")

class Bai: Flower2("Bai")


interface FlowerCare<in T: Flower2> { // 作为函数参数 可以是本type或者supertype
    fun trendFlower(f:T);
}

class Garden3<T:Flower2>(val l:List<T>, val care:FlowerCare<T>)
{
    fun doWork(i:Int)
    {
        care.trendFlower(l[i])
    }
}


fun main(args: Array<String>) {

    val carer = object : FlowerCare<Flower2> {
        override fun trendFlower(f: Flower2) {
            println(f.name)
        }
    }

    val roseGarden = Garden3<Rose2>(listOf(Rose2(),Rose2(),Rose2()),carer)
    roseGarden.doWork(0)

    val baiGarden = Garden3<Bai>(listOf(Bai(),Bai(),Bai()),carer)
    baiGarden.doWork(0)
}
/*
 List<Number> 和 List<Float> 都是Type
 Number是Float的super Class
 List<Number> 是 List<Float>的 super Type
 List<Float>  是 List<Number> 的 sub Type
 但是 List<Number> 不是 List<Float> 的 super class

 协变和逆变都是针对generic class 扩展了generic type

 */

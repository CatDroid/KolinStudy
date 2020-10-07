package com.ueno.Generics

class Garden<T> (val flowers:List<T>)

// 类型参数声明 加上限声明
fun <T:Number> List<T>.convertToInt2():List<Int>
{
    return map { it.toInt() }
}

//Kotlin: Platform declaration clash: The following declarations have the same JVM signature (convertToInt2(Ljava/util/List;)V):
//fun convertToInt2(list: List<Number>): Unit defined in com.ueno.Generics in file ClassInterfaceGenerics.kt
//fun convertToInt2(list: MutableList<Number>): Unit defined in com.ueno.Generics in file ClassInterfaceGenerics.kt
// List<Number> 和  MutableList<Number> ?? javaclass都是  Ljava/util/List ??


fun convertToInt2(list:List<Number>) // 这个声明了参数类型是个接口 不是声明类 java.util.List这个也是接口
{
    list.forEach { println("${it.toInt()}") }
}

//@JvmName("MconvertToInt2")
fun MconvertToInt2(list:MutableList<Number>)
{
    list.forEach { println("${it.toInt()}") }

    list.add(12)
}

/*
    kotlin covariance 协变

    public interface List<out E> : Collection<E>
    使用out修饰 类型参数

    public interface MutableList<E> : List<E>, MutableCollection<E>
    没有使用out修饰通用类型/泛型参数

    对应于java的extends和super

    对于 out 泛型，我们能够将使用子类泛型的对象赋值给使用父类泛型的对象
    如果你的类是将泛型作为内部方法的返回，那么可以用 out

    对于 in 泛型，我们可以将使用父类泛型的对象赋值给使用子类泛型的对象。
    如果你的类是将泛型对象作为函数的参数，那么可以用 in：

    in和out只是限定类中的成员函数
    in是成员函数的参数
    out是成员函数的返回类型 不能作为成员函数的输入参数 防止他的instance被成员函数改变
                       如果一定要把他作为成员函数参数类型 就要用 (inputArg:@UnsafeVariance E)
                       而且要保证 这个instance 不会被成员函数修改

    使用out修饰的类型 一般只作为成员函数的返回类型
    使用out修饰类型参数的类 成为 协变类
    使用 协变类 作为 函数输入参数 在函数调用时候 实参可以是本数据和子数据类型

    super-type -- in --> sub-type
    sub-type --> out --> super-type

    一般只作为成员函数的返回类型
    作为函数参数 可以用本类或者子类作为实参
    没有out声明 只能用一样的
*/
fun main(args: Array<String>) {

    // println( "1" in listOf("a","b","c")) // 这个in其实是调用 后面对象的 contains 函数
    // println( 1 in listOf("a","b","c")) // 错误


    // MutableList 和 List都只是接口  实现类 可以是
    // mutableListOf class java.util.ArrayList  listOf class java.util.Arrays$ArrayList
    val test11: MutableList<Number> = mutableListOf(1,2,3);
    val test12: List<Number> = listOf(1,2,3);
    println("mutableListOf ${test11.javaClass}  listOf ${test12.javaClass}")


    val g = Garden(listOf("Rose", "Daisy","Daffodil"))
    println("Flowers in this garden: ${g.flowers}")


    val l = listOf(1.0,20.0,3.4);
    convertToInt2(l); // immutable的 List<Double> --> List<Number> ok

    //val l2 = mutableListOf(1.0,2.0,3.0)
    //MconvertToInt2(l2); // mutable的 List<Double> --> List<Number> not match

    var oneRose = Rose(123);
    var roseGarden = Garden2(listOf(oneRose))
    println("before ${oneRose.id}")
    println(roseGarden.containFlower(oneRose))
    println("after ${oneRose.id}")

}



open class Flower(var id:Int)

class Rose(id:Int): Flower(id)

/*
    https://www.kotlincn.net/docs/reference/generics.html

    out 修饰符 可以 标注 Garden2 的类型参数 T

    来确保它仅从 Garden2<T> 成员中返回T（生产），并从不被消费(作为成员函数的参数)

    一般原则是：
    当一个类 C 的类型参数 T 被声明为 out 时，它就只能出现在 C 的成员的输出-位置，
    但回报是 C<Base> 可以安全地作为 C<Derived>的超类。

    简而言之，他们说类 C 是在参数 T 上是协变的，或者说 T 是一个协变的类型参数。

    "你可以认为 C 是 T 的生产者，而不是 T 的消费者"

    "out修饰符称为型变注解，并且由于它在类型参数声明处提供，所以我们称之为声明处型变"

    "这与 Java 的使用处型变相反，其类型用途通配符使得类型协变"

 */
class Garden2<out T:Flower> (val flowers: List<T>)
{
    fun pickFlower(i:Int):T  // out参数类型 作为成员函数的返回值
    {
        return flowers[i];
    }

    fun containFlower(flower:@UnsafeVariance T):Boolean // out参数类型 作为成员函数参数 必须 UnsafeVariance
    {
        // 必须保证函数不修改 flower
        flower.id = 12 ;// 但是修改了也不会报错
        return flower in flowers
    }
}

fun waterGarden(ganden: Garden2<Flower>)
{

}

fun maintainGarden(roseGarden: Garden2<Rose> )
{
    waterGarden(roseGarden) // 泛型子类-泛型基类 必须类型参数加上out才可以
}

// Contravariance  参数逆变

//interface Comparable<in T> {
//    operator fun compareTo(other: T): Int
//}

fun demo(x: Comparable<Number>) {
    // compareTo(T) T是Number 只能传入Number或子类  C<in T>是T的消费者

    x.compareTo(1.0) // 1.0 拥有类型 Double，它是 Number 的子类型
    // 因此，我们可以将 x 赋给类型为 Comparable <Double> 的变量
    val y: Comparable<Double> = x // OK！
}

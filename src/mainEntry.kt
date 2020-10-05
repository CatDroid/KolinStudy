import com.Fela.MyObject.MySingleton
import com.hozon.MyNewPackage.DataClass
import com.hozon.MyNewPackage.PackageClass
import com.hozon.MyNewPackage.PackageClass2
import com.hozon.MyNewPackage.upper3FirstAndLast3
import com.hozon.MyNewPackage.lastOne
import com.may.MyInterface.OneClass

// 没有namespace控制??
val GREETING_CONSTANT = "hello world" // static final String

// 编译期常量  可以用在注解 @Deprecated(SUBSYSTEM_DEPRECATED)
// 原生类型和字符串 没有自定义的get和set 就可以加const
const val SUBSYSTEM_DEPRECATED: String = "deprecated"


fun myFun(args: Array<String>) {
    println("My Function Called ${GREETING_CONSTANT}");
}


fun main(args: Array<String>) {

    myFun(args);
    println("Hello World");

    // var 是variable 的缩写，val 是value 的缩写
    val finalVar:Short = 1 ;

//    finalVar = 2;

    var modifyVar:Boolean = false ;

    modifyVar = true ;

    println("finalVar " + finalVar + " modifyVar " + modifyVar);

    /**
     * kt的基础数据类型 都是 Class
     * kt的datetype Class 在运行时候 就是JVM的原始数据类型
     */
    var sshort:Short = 250;
    var bbyte: Byte = 12 ;


    // Char是个类型 跟Java的char不一样 并且类型之间没有父子关系  需要调用方法转换
    //var c:Char = 65 as Char ; // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.Character
    var c:Char = 65.toChar();  // 除了Boolean类型 其他基础类型 都定义了对应的toXXX();
    println(c);


    var d:Int = 12 ;
    //var dl:Long = d; // type mismatch
    var dl:Long = d.toLong();
    println(dl);

    //var dll = dl + c ; // Long的operator 没有定义 与 Char数据类型的
    var dll = dl + d ;  // Long数据类型 定义的方法  public final operator fun plus(other:Int):Long


    println("dll is Long ? ${dll is Long}") // 用is代替instanceof   打印使用字符串模版
    println("${dl} + ${d} = ${dl+d}")       // 字符串模版  ${表达式}

    // 转义字符串
    // 原生字符串/三个双引号
    println("""this is "raw \n string" ${dl+d} """) // 原生字符串 也可以包含 字符串模版

    println("""this is raw string 
        |边界前缀
        |前面的包含空格和前缀字符
        |都会被删除
    """.trimMargin()) // 如果不加trimMargin 就会原样输出

    // === 等同与java的== 用来判断引用对象是否同一个    ==和equal都等于原来的equal

    var cat = Animal("cat", true); // Kotlin 会把 DataType Class 编译后成为primitive type  Boolean->boolean
    var cat2 = Animal("cat", true);
    var dog = Animal("dog", true);
    println("cat === cat2 ? ${cat === cat2}");  // false
    println("cat == cat2 ? ${cat == cat2}");    // true
    println("dog == cat ? ${cat == dog}")       // false


    /**
     * Any    Object 所有类的基类
     * Unit   相当于函数返回void 不是类型 只是编译器用的 如果函数要返回Unit 就要返回这个单例对象
     * Nothing 所有类的子类  不存在的值  函数如果返回Nothing代表永远不返回
     */

    println("cat.name ${cat.name }"); // 不需要调用GetName和SetName


    // 数组

    var animals = arrayOf(Animal("cat",true), Animal("dog",false), Animal("bird",false))

    var intArray = intArrayOf(1,2,3,4);// IntArray 对应 java的int[]  primitive array type
    var ArrayInt = arrayOf(1,2,3,4); // 通用数组 --> 原生数组
    var primitiveIntArray = ArrayInt.toIntArray();
    // primitiveIntArray.toTypedArray() 转换成kt的数据类型
    animals[1].dumpIntArray(primitiveIntArray);

    var arrayLong = arrayOf(1,2,3);
    var arrayLong1:Array<Long> = arrayOf(1,2,3);

    // Error:(74, 48) Kotlin: Cannot check for instance of erased type: Array<Long>
    //println("arrayLong is int ? ${arrayLong is Array<Long>}")
    println("arrayLong is int ? ${arrayLong is Array<Int>}")

    arrayLong1[2] = 3 ;
    // public operator fun set(index: Int, value: T): Unit
    //  public operator fun get(index: Int): T

    //arrayLong1[5] = 4 ; // java.lang.ArrayIndexOutOfBoundsException: 5

    // 创建数组的两种方式 arrayOf 和 数组构造函数/lambda 两个参数 大小和函数pattern
    val evenNumber = Array(5, { x -> x * 2}) ;
    println(evenNumber) // [Ljava.lang.Integer;@3cd1a2f1

    // Array<Any> 数组的元素类型不一样
    var mixedArray = arrayOf("hello", Animal("cat",false), 10.0); // 复合类型的数组

    for (one in mixedArray) {
        println("${one} ------ Class is-----> ${one.javaClass}")
    }


    val nullArray = arrayOfNulls<Int>(5); // 只是给大小，没有初始化(lambada)的通用数组
    nullArray[2] = 123 ;
    for (one in nullArray) {
        println(one)
    }


    var myCat = Cat("myCat", false);
    var myAnimal = myCat as Animal; // as 类型不安全
    myAnimal.Set();

    //myAnimal.CatMethod(); // 这个是错误的
    if (myAnimal is Cat) {
        myAnimal.CatMethod(); // 由于有上面的条件 这里智能转换 Smart Cast
    }

    // Exception in thread "main" kotlin.TypeCastException: null cannot be cast to non-null type Cat
    try {
        val temp1 = null as Cat ;
        temp1.Set();
    } catch (ex: kotlin.TypeCastException) {
        println("NPE Exception");
    }

    // 定义变量时，可在类型后面加一个问号?，表示该变量是Nullable，不加表示该变量不可为null
    // 相当于 @Nullable @NotNull
    // 如果该变量为null，则不执行该变量调用的方法
    // "?"加在变量名后，系统在任何情况不会报它的空指针异常
    // "!!"加在变量名后，如果对象为null，那么系统一定会报异常
    var str : String? = null

    if (str != null) {
        str.toDoubleOrNull();
    }
    // Kotlin为我们提供的检验空指针的方法
    str?.toDoubleOrNull();

    // ? 可以把原来所有null判断缩写了
    var cat1 = Cat("CatFood", true);
    val isNullableString = cat1.mFood?.food?.name?.toString()
    // isNulableString 的类型是 String? 可以为null
    //println(isNullableString.toInt())  // 这样调用会报错 必须加问号
    println(isNullableString?.toInt())

    // ?: 如果问号前面的表达式返回null 就使用冒号后面的值
    val isNonNullString = cat1.mFood?.food?.name?.toString()?:"Default value"
    // isNonNullString 的类型是 String 不可能是null
    println("isNonNullString is ${isNonNullString}")

    val doubleDefault = cat1.mFood?.food?.name?.toString()?:cat1.mFood?.food?.name?:"default'default"
    println("doubleDefault is ${doubleDefault}") // 使用了两次:


    val ifStatement = if (cat1 == null) { "Null" }else{ "NotNull"} // if 表达式
    // if 描述语 --java/kotlin都有

    val doNotBreakup = cat1 as? String ; // as? 安全的类型转换
//    if (doNotBreakup == null) {
//        throw Exception();
//    } else {
//
//    }
    doNotBreakup?.toInt(); // 为空的话 不执行 也不抛出错误 返回null
    //doNotBreakup!!.toInt(); // 为空的话 抛出 kotlin的NPE 不是Java的NPE  kotlin.KotlinNullPointerException

    try {
        val npeException = doNotBreakup!! // Exception 发生在判断的地方 而不是调用的地方 Java做不到
        npeException.toInt();
    } catch(ex: kotlin.KotlinNullPointerException ) {
        println(ex.stackTrace);
    }


    val nullableString:String? = null
    var nonNulableString:String = "Hello World"
    println( nullableString == nonNulableString)
    // 虽然 nullableString 是null 但是没有报错 不会NPE

    val nullableString1:String? = "John"
    //printName(nullableString1); nonnull和nullable不是同个类型 ！
    //printName(nullableString1!!) // !! 转换成nonnull 否则抛出异常

//    if (nullableString1 != null) {
//        val copy = nullableString1; // 避免在多线程环境修改了nullableString1的指向
//        printName(copy);
//    }

    // safe operator  it只能在{}有效  let接受lambda作为参数 lambda的参数是调用let的对象
    // let 界定范围的函数
    nullableString1?.let({printName(it)}) // 上面等同 如果是 mutable variable 只能用it
    nonNulableString.let({printName(it)}); // 如果是Immutable variable 可以是变量名字或者it

    /////////////

    var dataClassInstance = DataClass("Hello", 11);
    println("DataClass = ${dataClassInstance}")
    // func copy(name:String = this.name, age:Int = this.age)
    // = DataClass(name,age) ;
    var copyOfDataClass = dataClassInstance.copy("Tom"); // 没有赋值的用原来实例的
    println("DataClass.copy = ${copyOfDataClass}")

    val packageClsObj = PackageClass2("Ho", 123)
    println(packageClsObj.gender);
    // println(packageClsObj.mIsMarried); // mIsMarried is private, get函数也是private

    //PackageClass().defaultArgsValue(null, 13) //  位置参数  因为参数类型不是Stirng? 不能传入null
    PackageClass().defaultArgsValue( temp1 = 13) // 命名参数  如果参数的名字不存在，会提示错误

    println("不定长度参数 ")
    //printNames(dataClassInstance, copyOfDataClass);

    // public inline fun <reified @PureReifiable T> arrayOf(vararg elements: T): Array<T>
    //printNames(arrayOf(dataClassInstance, copyOfDataClass))// 不匹配 形参是DataClass 实参是Array<DataClass>
    printNames(*arrayOf(dataClassInstance, copyOfDataClass)); // 解剖运算符 *

    printIdNames(dataClassInstance, copyOfDataClass, id = 2);


    val extensString:String = "Hello world"
    println(extensString.upper3FirstAndLast3()) // 必须import函数 才能在这个文件扩展String的方法和属性
    println(extensString.lastOne);

    var iface = OneClass(12);
    println("interface non-abstract field ${iface.number2}" ); // 12*3 = 36


    println("MySingleton called first ${MySingleton.getTag()}")
    println("MySingleton ${MySingleton.getCopyRight()}")
    println("MySingleton Class Base ${MySingleton.id} ")

}


fun printName(name: String)
{
    val outerName:String = "local use outer function variable"
    fun localFun(n:String) { // 内部函数可以使用 外部函数的局部变量
        println("${outerName} , name is ${n}")
    }
    localFun(name);
}


// 一个函数只能有一个vararg参数
internal fun printNames(vararg dcs:DataClass)
{
    for (dc in dcs)
    {
        println(dc);
    }
}

// vararg 不是在参数列表最后一个，其后的参数都要用命令参数  集合函数大部分使用vararg参数 比如arrayof()
internal fun printIdNames(vararg dcs:DataClass, id:Int)
{
    println("id = ${id}")
}




fun main(args: Array<String>) {

    val cat = Cat("hello",false );

    // T! means "T or T?" 可能为null也可以不是null  platform-type平台类型
    // kotlin不可能自己创建这种类型 只能在与其他语言交互时候
    // "types of unknown nullability
    val unknownNull = cat.nullOrNonNullText;

    // 如果有注解 @nullable 在kotlin就是 T?
    val nullableText = cat.nullableText
    //println(nullableText.javaClass); // 这个type是String! 可空字符串类型

    val nonText:String = cat.nonNullText

    // 必须使用org.jetbrains.annotations.NotNull才有作用,在编译的时候IDE已经提示
    //cat.nonNullText = null


    val string = arrayOf("Hello", "World")
    val void = cat.variableNumMethod(*string); // 不能传递数组 要用扩展运算符
    println("void JavaMethod() = ${void} ${void.javaClass}")

    // 要获取class不用调用getClass 直接调用javaClass即可
    val obj = cat.getObject(); // obj类型是 Any
    println("Java Object in kotlin ${obj.javaClass}") //  class java.lang.Object

    val javaObj = obj as java.lang.Object ;

    // 在kotlin中synchronized是个函数 block是个lambda表达式
    synchronized(javaObj) {
        // 必须加 synchronized
        // 否则抛出异常 java.lang.IllegalMonitorStateException

        javaObj.notify(); // 调用Object的实例的成员方法
    }


    // 调用java的静态方法
    Cat.javaStaticMethod();

    // 单一抽象方法接口（SAM，Single Abstract Method Interface  在Java8叫做 函数接口
    // 可以使用lambda替代匿名实例
    //
    // Java引入了@FunctionalInterface注解。用了它，你定义的接口就得是只有一个抽象方法的函数接口，否则会报编译错误
    //
    // cat.demoSam3 ({  -> println("kotlin runnable ") })
    cat.demoSam3  {println("kotlin runnable ")}

    // 自定义的函数接口(带参数和返回值  Java8 SAM 单一抽象方法接口)
    cat.demoSam4( {arg1:String, arg2:Int -> println("arg1 is ${arg1}") ; println("arg2 is ${arg2}"); true } )
}
package com.yori.Logic

/*

https://www.cnblogs.com/Jetictors/p/8647888.html
https://blog.csdn.net/achenyuan/article/details/81565565
https://www.jianshu.com/p/504bca3a9fa1 成员引用

Kotlin中提供了简洁的语法去定义函数的类型

() -> Unit              // 表示无参数无返回值的Lambda表达式类型

(T) -> Unit             // 表示接收一个T类型参数，无返回值的Lambda表达式类型

(T) -> R                // 表示接收一个T类型参数，返回一个R类型值的Lambda表达式类型

(T, P) -> R             // 表示接收一个T类型和P类型的参数，返回一个R类型值的Lambda表达式类型

(T, (P,Q) -> S) -> R    // 表示接收一个T类型参数和一个接收P、Q类型两个参数并返回一个S类型的值的Lambda表达式类型参数，返回一个R类型值的Lambda表达式类型



    // lambda
    val test : (Int , Int) -> Int = {a , b -> a + b}
    // 或者
    val test = {a : Int , b : Int -> a + b} // 不写test的类型 直接自动推导

    有参数的情况
    val/var 变量名 : (参数的类型，参数类型，...) -> 返回值类型 = {参数1，参数2，... -> 操作参数的代码 }
    // 等价写法：即表达式的返回值类型会根据操作的代码自推导出来。
    val/var 变量名 = { 参数1:类型， 参数2:类型, ... -> 操作参数的代码 }

    大括号 : Lambda 表达式需要完整的写在大括号中 , 其参数列表 , 执行代码 , 返回值 , 都写在一个大括号 {} 里 ;
    参数列表 : Lambda 表达式的参数列表 , 定义在 “->” 符号之前 , 格式为 “变量名 : 变量类型” , 多个参数使用逗号隔开 ;
    函数体 : Lambda 表达式的函数体可以由 1 行 , 也可以有多行 , 最后一行默认是 Lambda 表达式的返回值 ;
    -> 符号 : 用于分割 参数列表 与 函数体 ;

 */
fun getFunc(multi:Int = 10): (Int , Int) -> Unit {

    val test : (Int , Int) -> Unit = {a , b -> // 参数存在,写在 {} 中的 -> 前
            val temp = "lambda function body"          // 函数体存在,写在 {} 中的 -> 后
            println("${temp} value = ${a * multi + b}")
    }

    // test 变量
    // (Int , Int) -> Unit  lambda的类型
    // {形参1，形参2... -> {函数体} }
    //


    return test ;
}

data class Employee(val name:String, var startYear:Int)


fun main(args: Array<String>) {

    val lamFunc = getFunc(20)

    lamFunc(10,5);

    // lambda表达式 放在 {} 中
    val isOddNumber = { number: Int ->
        println("number is $number")
        number % 2 == 1
    }
    println(isOddNumber.invoke(100))


    // Lambda内不允许使用return语句。 如果要从lambda返回，则应使用标签
    val fullLambda : (Int,Double)->Boolean = {
            number:Int,number2:Double  -> Boolean // lambda表达式中函数参数不能用() 没有参数就留空
            if (number.toDouble() == number2) {
                //return true ;
                true ;
            } else  {
                false ;
            }//  在非内联的Lambda表达式中不能使用 return
    }// https://www.jianshu.com/p/3589a2d00625
    // 调用内联函数传递的lambda可以使用return+标记符 默认从内联函数返回
    println("非内联的Lambda表达式中不能使用return ${fullLambda(1,2.2)}")

    val noArguReturnLambda : () -> Unit  = {
          -> println("没有参数和返回值的lambda")
    }

    println("${noArguReturnLambda()}") // 打印是kotlin.Unit

    val employees = listOf(Employee("aaa",1995),
        Employee("bbb", 1987),
        Employee("ccc", 1990)
    )

    // { param1 : Type1, param2 : Type2 , … -> body }
    // lambda中的
    // lambda作为参数最后是lambda的话，可以放在()之外
    // 只有一个lambda参数可以不加()(相当于放在()之外)
    // 编译器标志符it 可以不用写参数 (T)-> 可以省略 函数体中用it代替
    employees.forEach  {
             employee  ->
        println("name $employee.name, year ${employee.startYear}") }

    //
    // public inline fun <T, R : Comparable<R>> Iterable<T>.minBy(selector: (T) -> R): T?
    //
    // public inline fun
    // <T, R : Comparable<R>>    T R 模板的类型参数
    // Iterable<T>.              Iterable<T> 是个类
    // minBy(                    minBy 是个函数, 接受参数是
    // selector: (T) -> R):      selector , 是个接受一个参数(跟Array<T>类型T一样)并返回一个参数的lambda(必须实现了Comparable<R>)
    // T?                        返回类型是 T? 可能是nil
    println(employees.minBy { employee -> employee.startYear }) // 参数是一个模板 selector: (T) -> R

    // 只有一个参数 可以不写参数列表 函数体直接用it代替
    println(employees.maxBy { it.startYear })


    var number = 5 ;
    run {
        number += 5;
    }

    run {
         -> println("Nothing to do")  // lambda没有参数的时候 不用() 直接留白 同时->也可以省略
    }
    println("run number ${number}") // run number 10


    // 方法引用（method references） ? 成员引用(member references)
    //
    // 这种语法用来替代某些特定形式Lambda表达式。
    // 如果Lambda表达式的全部内容就是调用一个已有的方法，那么可以用方法引用来替代Lambda表达式。
    //
    // 如果要当做参数传递的代码已经被定义成了函数，这时候就需要 把函数转换成一个值，这种方式称为 成员引用
    //
    // 调用单个方法或者访问单个属性的函数值，双冒号把 类名称 与 你要引用的成员（一个方法或者属性）名称 隔开

    println("成员引用 ${employees.maxBy(Employee::startYear)}")
    run(::topLevelFunction);
}


fun topLevelFunction() = println("topLevelFunction ")

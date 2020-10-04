


// default is public
class KotlinMultiClass {
}

// Kotlin Top-Level Item or 类成员 默认都是public

// Top-Level
// Kotlin 一个文件可以有多个public Class 可以有private class
// Java  一个文件只能有一个Class 所以private class也是无用的
// 在Java只能是class在top level   class只能是public或者package-private
// 在Kotlin Top Level 包含 class/properties/function
// Kotlin 一堆文件一起编译的 就是模块  internal可一给同一个模块所有源文件可见/可访问 没有了package-private了

// Class/interface-level 修饰类成员
// kotlin default is public
// kotlin protected 只能是定义类和子类, java可以定义类和子类/同个包
//
public class ClassA
{
    private var mName:String = "";
    fun ClassA(name:String)
    {
        mName = name ;

        print(ClassB().obj.mPublic);

    }
}

private class ClassC
{
    fun dumpClassC()
    {
        println("This is ClassC");
    }
}

private fun TopLevelFunction(temp:String)
{
    val objC:ClassC = ClassC();
    println(temp);
    println(objC);
}

//            Compiler  ByteCode
// private    ------->  package private
// internal   ------->  public (为了解决在Java访问,在bytecode会用很长的名字替换)
public class ClassB
{
    val obj:InnerClass = InnerClass();
    private val objC:ClassC = ClassC(); // public Class中的属性 不能是public的private class对象
    // public val objD:ClassC = ClassC(); 不能够这样,因为ClassC是private的 所有成员都不能在外部访问 所以没有意义

    fun ClassB()
    {
        //obj.mId; // 在Java可以 Kotlin不可以 Kotlin的内部类 成员有真正的private
        obj.mPublic; // it's ok
        TopLevelFunction("called by ClassB() constructor");
    }

    public class InnerClass()
    {
        private var mId:Int = 123;
        public val mPublic:Double = 1.2 ;
    }

}
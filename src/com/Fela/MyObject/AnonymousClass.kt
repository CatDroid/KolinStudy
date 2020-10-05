package com.Fela.MyObject

interface OneInterface {
    fun getString(temp:Int):String
}

fun dumpInfo(invoke:OneInterface) {
    println(invoke.getString(110));
}

class AnonymousClass(val id:Int) {


}
/*
   @NotNull
   public static final OneInterface getInvoke() {

      final ObjectRef obj = new ObjectRef();
      obj.element = new AnonymousClass(12);

      final IntRef number = new IntRef();
      number.element = 13;

      <undefinedtype> temp = new OneInterface() {
         @NotNull
         public String getString(int temp) {
            return "getInvoke temp is " + temp + ", local variable is " + number.element + " , " + ((AnonymousClass)obj.element).getId();
         }
      };

      number.element = 14; // 还是那个Object 只是修改了内部
      obj.element = new AnonymousClass(15);

      return (OneInterface)temp;
   }

 */
fun getInvoke(): OneInterface {

    var obj = AnonymousClass(12); // java里面匿名对象引用的局部对象必须是final,kotlin可以是var或者val
    var number:Int = 13 ;
    val temp = object:OneInterface{ // 匿名对象--对象表达式
        override fun getString(temp: Int): String {
            // 可以引用包含他的作用域
            return "getInvoke temp is ${temp}, local variable is ${number} , ${obj.id}"
        }

    }
    number = 14 ;
    obj = AnonymousClass(15);

    return temp
}

fun main(args: Array<String>) {

    val temp = getInvoke();
    dumpInfo(temp);// getInvoke temp is 110, local variable is 14 , 15

    // object关键字出现他们都遵循同样的核心理念：这个关键字定义了一个类，并创建了该类的实例，
    // 也就是说用object关键字在定义该类的同时创建了该类的对象；

    var coords = object {

        // 不需要次构造函数
        val x = 410 ;
        val y = 1028;

        fun add() = x+y

    }
    println("coords is ${coords.x}, ${coords.y} = ${coords.add()}")

}
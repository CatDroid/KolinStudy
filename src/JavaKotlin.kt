


fun main(args: Array<String>) {

    val cat = Cat("hello",false );
    val text = cat.text
    val nonText:String = cat.nonNullText
    //cat.nonNullText = null  // 必须使用org.jetbrains.annotations.NotNull才有作用

    // T! means "T or T?" 可能为null也可以不是null  kotlin不可能自己创建这种类型 只能在与其他语言交互时候
    // "types of unknown nullability


}
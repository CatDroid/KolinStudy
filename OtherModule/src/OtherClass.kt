import com.dali.EnumClass.Department as DP // 重新命名

class OtherClass {

}

fun main(args: Array<String>) {
    println(DP.SALES.fullName);
    // 引用其他模块  需要 project structure--选择当前模块--dependency--+ module ---选择依赖的模块
}
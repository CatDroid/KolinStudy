package com.coco.CallKotlinFromJava.java;

//import com.coco.CallKotlinFromJava.kotlin.SchoolKt;
import com.coco.CallKotlinFromJava.kotlin.MySchool;
import com.coco.CallKotlinFromJava.kotlin.SingleOne;
import com.coco.CallKotlinFromJava.kotlin.StaticSchool;


import java.io.IOException;
import java.security.Permission;

import static java.lang.System.out;

public class MainEntry {

    public static void main(String[] args) {


//        out.println("Call kotlin top-level function result = " +
//                SchoolKt.myTopLevelFunction(111, "kotlin"));

        out.println("Call kotlin top-level function result = " +
                StaticSchool.myTopLevelFunction(111, "kotlin"));



        StaticSchool.print("Hello World");


        MySchool m = new MySchool("Hello", false, 1998);

        // boolean类型自动生成的Get和Set名字 有改动
        m.isPublic();
        m.setPublic(true);// 不是setIsPublic 去掉了Is

//        m.setName() // kotlin只会为var的属性生成set和get方法 val的属性只有get方法

        // 如果需要在java中直接访问kotlin类中的实例属性 需要加注解 jvmField (不能是private属性)
        out.println("call kotlin class.field = " + m.year);

        out.println("call kotlin top-level const " +
                com.coco.CallKotlinFromJava.kotlin.StaticSchool.SCHOOL_DEPRECATED);

        out.println("call kotlin companion function " +
                MySchool.Companion.kotlinStaticMethod("java") );

        out.println("call kotlin companion function " +
                MySchool.Companion.kotlinStaticMethod_JVMStatic("java") );

        out.println("call kotlin companion function " +
                MySchool.kotlinStaticMethod_JVMStatic("java") );

        // 调用kotlin的单例 需要使用 INSTANCE
        SingleOne.INSTANCE.doSomeThing();

        // 访问Companion object的属性
        MySchool.Companion.getIS_KOTLIN();

        // const的 Companion object的属性 没有set和get 也不需要JvmField 就可以直接访问
        out.println(MySchool.IS_JAVA);

        try {
            SingleOne.INSTANCE.nonNullableArg(null);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }


        try {
            SingleOne.INSTANCE.throwIoException();
        } catch (IOException ex){
            /*
                              Throwable
                                 ｜
                              Exception
                     ｜                    ｜
                 RuntimeException       IOException
                     ｜
                IllegalArgumentException
             */
            ex.printStackTrace();
        }



        SingleOne.INSTANCE.defaultArg("hello");
    }
}

package com.coco.CallKotlinFromJava.java;

//import com.coco.CallKotlinFromJava.kotlin.SchoolKt;
import com.coco.CallKotlinFromJava.kotlin.StaticSchool;
import static java.lang.System.out;

public class MainEntry {

    public static void main(String[] args) {


//        out.println("Call kotlin top-level function result = " +
//                SchoolKt.myTopLevelFunction(111, "kotlin"));

        out.println("Call kotlin top-level function result = " +
                StaticSchool.myTopLevelFunction(111, "kotlin"));



        StaticSchool.print("Hello World");
    }
}

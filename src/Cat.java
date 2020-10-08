//import com.sun.istack.internal.NotNull;


import org.jetbrains.annotations.Nullable ;
import org.jetbrains.annotations.NotNull ;


/*
Android The import org.jetbrains cannot be resolved
Download the last version of annotations jar
https://repo1.maven.org/maven2/com/intellij/annotations/12.0/annotations-12.0.jar

@Nullable and @NotNull annotations introduced in IntelliJ IDEA
for catching NullPointerException's (NPE's)
through the Constant Conditions & Exceptions and @Nullable problem inspections.

* */
public class Cat extends Animal {

    private boolean isMale = false ;
    private String nullableText;
    private String nonNullText = "default";
    private String nullOrNonNullText ;

    public Cat(String name, boolean isMan)
    {
        super(name, isMan);
    }



    public void SetValue(boolean value) {
        System.out.println("Cat SetValue " + value);
    }

    public void CatMethod()
    {
        System.out.println("Call Java Cat Method");
    }


    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }


    public @Nullable String getNullableText() {
        return nullableText;
    }


    public void setNullableText(String name) {
        this.nullableText = name;
    }

    public @NotNull String getNonNullText() {
        return nonNullText;
    }

    public void setNonNullText(@NotNull String nonNullText) {
        this.nonNullText = nonNullText;
    }

    public String getNullOrNonNullText() {
        return nullOrNonNullText;
    }

    public void setNullOrNonNullText(String nullOrNonNullText) {
        this.nullOrNonNullText = nullOrNonNullText;
    }

    // 不定参数长度数组 但是类型都是String 跟kotlin的vararg一样
    public void variableNumMethod(String... args)
    {
        for (String string:args){
            System.out.println(string);
        }
    }

    public @NotNull Object getObject()
    {
        return new Object();
    }

    public static void javaStaticMethod()
    {
        System.out.println("call javaStaticMethod ");
    }

    public void demoSam() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is Runnable in a thread");
            }
        }).start();
    }

    public void demoSam2() {
        new Thread( () -> System.out.println("This is Runnable in a thread") ).start();
    }

    public void demoSam3(Runnable r) {
        new Thread( r ).start();
    }

    @FunctionalInterface
    static public interface CallMeWithArgs{
        boolean CallMe(String arg1, int arg2);
    }

    public void demoSam4(CallMeWithArgs r) {
        boolean result = r.CallMe("FunctionalInterface ", 2);
        System.out.println("call FunctionalInterface result = " + result);
    }



    ///////////////

    class Food {
        public Food1 food = new Food1("Cat's Food");
        class Food1 {
            public String name ;
            public Food1(String n) {
                //name = n ;
            }
        }
    }
    public Food mFood = new Food();


}

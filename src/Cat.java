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
    private String text  ;
    private String nonNullText = "123";

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


    public String getText() {
        return text;
    }


    public void setText(String name) {
        this.text = name;
    }

    public @NotNull String getNonNullText() {
        return nonNullText;
    }

    public void setNonNullText(@NotNull String nonNullText) {
        this.nonNullText = nonNullText;
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

import java.util.ArrayList;
import java.util.Objects;

public class Animal
{

    private String name = "123";
    private boolean isMan = false ;

    public Animal(String name, boolean isMan) {
        this.name = name;
        this.isMan = isMan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return isMan == animal.isMan &&
                Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isMan);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", isMan=" + isMan +
                '}';
    }

    public void dumpIntArray(int[] array)
    {
        for (int one : array)
        {
            System.out.println(one);
        }
    }

    public void Set() {
        //this.SetValue(true);;
        Animal:SetValue(true);
    }


    public void SetValue()
    {
        ArrayList<String> temp = new ArrayList<>();

        //ArrayList<Object> temp1 =  temp;  // 错误 不兼容 泛化的目的就是为了解决Object到具体类的CastCastException 直接把具体Class到Object是不安全的

        // Java的泛型和C++的模版 不一样的地方  不会导致类型膨胀

        //if (temp instanceof ArrayList<String>) { // 编译后是没有泛化信息 所以这句无用 错误 不能在运行时候判断类型
//
        //}
    }

    public void SetValue(boolean value)
    {
        System.out.println("Animal SetValue " + value);
    }

}

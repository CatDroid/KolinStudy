public class Cat extends Animal {

    private boolean isMale = false ;

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

    public Cat(String name, boolean isMan) {
        super(name, isMan);
    }

    public void SetValue(boolean value) {
        System.out.println("Cat SetValue " + value);
    }

    public void CatMethod()
    {
        System.out.println("Call Java Cat Method");
    }


}

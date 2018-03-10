package ee.ttu.iti0202.cars.driver;

public class Human implements Driver {

    @Override
    public void speak() {
        System.out.println("Hello");
    }
}

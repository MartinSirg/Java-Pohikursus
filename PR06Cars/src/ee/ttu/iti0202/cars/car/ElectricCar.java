package ee.ttu.iti0202.cars.car;

import ee.ttu.iti0202.cars.driver.Driver;

public class ElectricCar extends Car {

    public ElectricCar(Driver driver) {
        super(driver);
    }

    @Override
    void drive(long distance) {
        System.out.println("Blink blink");
        super.drive(distance);
    }
}

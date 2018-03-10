package ee.ttu.iti0202.cars.car;

import ee.ttu.iti0202.cars.driver.Driver;

public class CombustionEnginedCar extends Car {

     CombustionEnginedCar(Driver driver) {
        super(driver);
    }

    @Override
    void drive(long distance) {
        System.out.println("Wroom");
        super.drive(distance);
    }
}

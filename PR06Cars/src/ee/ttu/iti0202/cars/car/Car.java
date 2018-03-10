package ee.ttu.iti0202.cars.car;


import ee.ttu.iti0202.cars.driver.Driver;

public abstract class Car {
    Driver driver;
    private long distanceDriven;

    Car(Driver driver) {
        this.driver = driver;
    }

    void drive(long distance) {
        distanceDriven += distance;
        driver.speak();
    }

    public long getDistanceDriven() {
        return distanceDriven;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}

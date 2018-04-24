package ee.ttu.iti0202.depot.train;

import ee.ttu.iti0202.depot.cargo.Cargo;

public class Car {

    private Cargo cargo;

    public Car() {

    }

    public Car(Cargo cargo) {
        this.cargo = cargo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Car setCargo(Cargo cargo) {
        this.cargo = cargo;
        return this;
    }
}

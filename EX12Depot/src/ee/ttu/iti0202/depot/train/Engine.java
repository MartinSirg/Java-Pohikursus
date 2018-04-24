package ee.ttu.iti0202.depot.train;

import ee.ttu.iti0202.depot.cargo.Cargo;

import java.util.ArrayList;
import java.util.List;

public class Engine {
    private int maxDanger;
    private List<Cargo.Type> forbiddenCargo = new ArrayList<>();

    public Engine(int maxDanger){
        this.maxDanger = maxDanger;
    }

    public Engine(int maxDanger, Cargo.Type... forbiddenCargo){
        this.maxDanger = maxDanger;
        this.forbiddenCargo = List.of(forbiddenCargo);
    }
    public int getMaxDanger() {
        return maxDanger;
    }

    public void setMaxDanger(int maxDanger) {
        this.maxDanger = maxDanger;
    }

    public List<Cargo.Type> getForbiddenCargo() {
        return forbiddenCargo;
    }

    public void setForbiddenCargo(List<Cargo.Type> forbiddenCargo) {
        this.forbiddenCargo = forbiddenCargo;
    }
}

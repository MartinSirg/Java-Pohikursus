package ee.ttu.iti0202.kt1.pack;

public abstract class Vehicle {
    private int maxWeight;
    private String regNum;

    Vehicle(int maxWeight,String regNum) {
        this.maxWeight = maxWeight;
        this.regNum = regNum;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }
}

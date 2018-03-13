package ee.ttu.iti0202.exceptions;

public class CoffeeMachineException extends Exception {
    public CoffeeMachineException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "CoffeeMachineException: " + super.getMessage();
    }
}

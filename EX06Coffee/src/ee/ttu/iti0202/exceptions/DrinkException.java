package ee.ttu.iti0202.exceptions;

public class DrinkException extends Exception {
    public DrinkException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "DrinkException: "  + super.getMessage();
    }
}

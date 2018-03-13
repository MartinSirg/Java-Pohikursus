package ee.ttu.iti0202.exceptions;

public class KitchenException extends Exception {
    public KitchenException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "KitchenException: " + super.getMessage();
    }
}

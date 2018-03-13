package ee.ttu.iti0202.exceptions;


public class WaterContainerException extends Exception {

    public WaterContainerException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "WaterContainerMessage: " + super.getMessage();
    }
}

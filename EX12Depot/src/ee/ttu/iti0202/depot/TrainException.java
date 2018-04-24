package ee.ttu.iti0202.depot;

public class TrainException extends Exception {
    public enum ExType {
        COLLIDING_CARGO  ("Incompatible cargo input. No train was created."),  //calls constructor with value 3
        NO_ENGINE("No engine found in depot."),  //calls constructor with value 2
        DANGER_OVERLOAD   ("Tried adding cargo to train but the engine's danger level was exceeded.")   //calls constructor with value 1
        ; // semicolon needed when fields / methods follow


        private final String message;

        private ExType(String levelCode) {
            message = levelCode;
        }

        String get() {
            return message;
        }
    }

    public TrainException(ExType type) {
        super(type.get());
    }
}

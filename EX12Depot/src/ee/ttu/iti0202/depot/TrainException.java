package ee.ttu.iti0202.depot;

public class TrainException extends Exception {
    public enum ExType {
        COLLIDING_CARGO("Incompatible cargo input. No train was created."),
        NO_ENGINE("No engine found in depot."),
        DANGER_OVERLOAD("Tried adding cargo to train but the engine's danger level was exceeded.");


        private final String message;

        ExType(String levelCode) {
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

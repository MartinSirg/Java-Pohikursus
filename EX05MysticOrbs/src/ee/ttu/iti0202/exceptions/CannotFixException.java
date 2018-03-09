package ee.ttu.iti0202.exceptions;

import ee.ttu.iti0202.oven.Oven;

public class CannotFixException extends Exception {
    public enum Reason { IS_NOT_BROKEN, FIXED_MAXIMUM_TIMES, NOT_ENOUGH_RESOURCES }
    private Reason reason;
    private Oven oven;

    public CannotFixException(Oven oven, Reason reason) {
        this.reason = reason;
        this.oven = oven;
    }

    public Reason getReason() {
        return reason;
    }

    public Oven getOven() {
        return oven;
    }
}

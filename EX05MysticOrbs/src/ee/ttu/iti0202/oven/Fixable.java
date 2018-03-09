package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.exceptions.CannotFixException;

public interface Fixable {
    void fix() throws CannotFixException;

    int getTimesFixed();
}

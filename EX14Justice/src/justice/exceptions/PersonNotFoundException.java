package justice.exceptions;

import justice.people.Person;

public class PersonNotFoundException extends Exception {
    private Person.Type missingPerson;

    public PersonNotFoundException(Person.Type type) {
        missingPerson = type;
    }

    @Override
    public String toString() {
        return "Could not find " + missingPerson + " in PoliceStation";
    }
}

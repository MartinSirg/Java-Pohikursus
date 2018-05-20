package justice.people;

import justice.policeStation.PoliceStation;

public class RegularPerson extends Person {
    private final static int MINIMAL_JUDGING_AGE = 18;

    RegularPerson(int age, String fullName, PoliceStation policeStation) {
        super(age, fullName, policeStation);
    }

    @Override
    public boolean canFileLawsuitAgainst() {
        return getAge() >= MINIMAL_JUDGING_AGE;

    }
}

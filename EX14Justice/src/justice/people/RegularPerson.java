package justice.people;

import justice.policeStation.PoliceStation;

public class RegularPerson extends Person {

    RegularPerson(int age, String fullName, PoliceStation policeStation) {
        super(age, fullName, policeStation);
    }

    @Override
    public boolean canFileLawsuitAgainst() {
        return getAge() >= 18;

    }
}

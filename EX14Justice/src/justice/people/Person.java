package justice.people;

import justice.policeStation.PoliceStation;

import java.util.Optional;

public abstract class Person {
    private int age;
    private String fullName;

    public enum Type { REGULAR, LAWYER, JUDGE }

    public static Optional<Person> of(Type type, int age, String fullName, PoliceStation policeStation) {
        if (type == Type.LAWYER && age < 25 || type == Type.JUDGE && age < 30) return Optional.empty();
        switch (type) {
            case JUDGE:
                return Optional.of(new Judge(age, fullName, policeStation));
            case LAWYER:
                return Optional.of(new Lawyer(age, fullName, policeStation));
            case REGULAR:
                return Optional.of(new RegularPerson(age, fullName, policeStation));
        }
        return Optional.empty();
    }

    Person(int age, String fullName, PoliceStation policeStation) {
        this.age = age;
        this.fullName =fullName;
        policeStation.addPerson(this);
    }

    public abstract boolean canFileLawsuitAgainst();

    public int getAge() {
        return age;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return getClass().getName().replace("justice.people.", "") + "= " + fullName + ", Age:" + age;
    }
}

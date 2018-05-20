package justice.people;

import justice.policeStation.PoliceStation;

import java.util.Optional;

public abstract class Person {
    private int age;
    private String fullName;
    private static final int MIN_LAWYER_AGE = 25, MIN_JUDGE_AGE = 30;

    public enum Type { REGULAR, LAWYER, JUDGE }

    public static Optional<Person> of(Type type, int age, String fullName, PoliceStation policeStation) {
        if (type == Type.LAWYER && age < MIN_LAWYER_AGE
                || type == Type.JUDGE && age < MIN_JUDGE_AGE) return Optional.empty();
        switch (type) {
            case JUDGE:
                return Optional.of(new Judge(age, fullName, policeStation));
            case LAWYER:
                return Optional.of(new Lawyer(age, fullName, policeStation));
            case REGULAR:
                return Optional.of(new RegularPerson(age, fullName, policeStation));
            default:
                return Optional.empty();
        }
    }

    Person(int age, String fullName, PoliceStation policeStation) {
        this.age = age;
        this.fullName = fullName;
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

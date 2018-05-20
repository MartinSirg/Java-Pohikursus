package justice.court;

import justice.people.Person;

import java.time.LocalDateTime;

public class Lawsuit {
    private static final int YEAR = 365, LIFE_IN_PRISON = 365000, THOUSAND = 1000, TEN_K = 10000, HUNDRED_K = 100000,
            TEN_YEARS = YEAR * 20, HUNDRED = 100, MONTH = 30;

    public enum Crime {
        MURDER(TEN_K, 3 * YEAR, HUNDRED_K, 365 * TEN_YEARS),
        THEFT(HUNDRED, 0, HUNDRED_K, YEAR),
        VIOLENCE(HUNDRED, 0, THOUSAND, 2 * MONTH),
        SCARING(0, 0, HUNDRED, MONTH);

        private final int minFine, minJail, maxFine, maxJail;

        Crime(int minFine, int minJail, int maxFine, int maxJail) {
            this.minFine = minFine;
            this.minJail = minJail;
            this.maxFine = maxFine;
            this.maxJail = maxJail;
        }

        public int getMinFine() {
            return minFine;
        }

        public int getMinJail() {
            return minJail;
        }

        public int getMaxFine() {
            return maxFine;
        }

        public int getMaxJail() {
            return maxJail;
        }
    }
    private Person person;
    private Crime crime;
    private LocalDateTime lawsuitFiledAt;


    public Lawsuit(Person person, Crime crime) {
        this.person = person;
        this.crime = crime;
        this.lawsuitFiledAt = LocalDateTime.now();
    }

    public Person getPerson() {
        return person;
    }

    public Crime getCrime() {
        return crime;
    }

    public LocalDateTime getLawsuitFiledAt() {
        return lawsuitFiledAt;
    }

    @Override
    public String toString() {
        return "Lawsuit against " + person.toString() + " for " + crime + " filed at " + lawsuitFiledAt;
    }
}

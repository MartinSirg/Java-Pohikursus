package justice;

import justice.court.Court;
import justice.judgments.Acquital;
import justice.judgments.Conviction;
import justice.people.Judge;
import justice.people.Person;
import justice.people.strategies.ConsiderCrimeStrategy;
import justice.people.strategies.ConsiderLawyerStrategy;
import justice.people.strategies.JudgeStrategy;
import justice.policeStation.PoliceStation;

import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    private static final int MAX_AGE = 100, MIN_JUDGE_AGE = 30, MIN_LAWYER_AGE = 25, MERCY_AGE = 25, LAWSUITS = 50;

    public static void main(String[] args) {
        Random random = new Random();
        Court court = new Court();
        PoliceStation station = new PoliceStation();
        for (int i = 0; i < 100; i++) {
            Person.of(Person.Type.REGULAR, random.nextInt(MAX_AGE), "Regular Person" + (i + 1), station);
        }
        for (int i = 0; i < 10; i++) {
            Person.of(Person.Type.LAWYER, MIN_LAWYER_AGE + random.nextInt(MAX_AGE - MIN_LAWYER_AGE),
                    "Lawyer Guy" + (i + 1), station);
        }
        Judge judge1 = (Judge) Person.of(Person.Type.JUDGE,
                MIN_JUDGE_AGE + random.nextInt(MAX_AGE - MIN_JUDGE_AGE), "Crime Judge 1", station).get();
        Judge judge2 = (Judge) Person.of(Person.Type.JUDGE,
                MIN_JUDGE_AGE + random.nextInt(MAX_AGE - MIN_JUDGE_AGE), "Crime Judge 2", station).get();
        Judge judge3 = (Judge) Person.of(Person.Type.JUDGE,
                MIN_JUDGE_AGE + random.nextInt(MAX_AGE - MIN_JUDGE_AGE), "Lawyer Judge", station).get();
        Judge judge4 = (Judge) Person.of(Person.Type.JUDGE,
                MIN_JUDGE_AGE + random.nextInt(MAX_AGE - MIN_JUDGE_AGE), "Age Judge 1", station).get();
        Judge judge5 = (Judge) Person.of(Person.Type.JUDGE,
                MIN_JUDGE_AGE + random.nextInt(MAX_AGE - MIN_JUDGE_AGE), "Age Judge 2", station).get();

        JudgeStrategy considerAgeStrategy = (lawsuit, lawyer) -> {
            if (lawsuit.getPerson().getAge() < MERCY_AGE) return new Acquital(lawsuit);
            return new Conviction(lawsuit, lawsuit.getCrime().getMinFine() * 2, lawsuit.getCrime().getMinJail());
        };

        judge1.setJudgeStrategy(new ConsiderCrimeStrategy());
        judge2.setJudgeStrategy(new ConsiderCrimeStrategy());
        judge3.setJudgeStrategy(new ConsiderLawyerStrategy());
        judge4.setJudgeStrategy(considerAgeStrategy);
        judge5.setJudgeStrategy(considerAgeStrategy);

        for (int i = 0; i < LAWSUITS; i++) {
            station.formLawsuit(court);
        }
        System.out.print("People who can be sued: ");
        System.out.println(station.getPeople().stream().filter(Person::canFileLawsuitAgainst)
                .collect(Collectors.toList()).size());
        System.out.print("People who have been jailed: ");
        System.out.println(station.getIncarceratedPeople().size());
        System.out.print("People who haven't been sued or got no jail time: ");
        System.out.println(station.getFreePeople().size());
    }
}

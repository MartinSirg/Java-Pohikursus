package justice.policeStation;

import justice.court.Court;
import justice.court.Lawsuit;
import justice.court.Litigation;
import justice.exceptions.PersonNotFoundException;
import justice.people.Judge;
import justice.people.Lawyer;
import justice.people.Person;
import justice.people.RegularPerson;


import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PoliceStation {
    private List<Person> people = new ArrayList<>();
    private List<Person> incarceratedPeople = new ArrayList<>();
    private Map<Person, List<Litigation>> archive = new HashMap<>();
    private Random random = new Random();


    public void addPerson(Person person) {
        people.add(person);
    }

    public void formLawsuit(Court court) {
        try {
            RegularPerson regularPerson = getRegularPerson();
            Lawyer lawyer = getLawyer();
            Judge judge = getJudge();
            //Picks random crime from all crimes
            Lawsuit.Crime crime = Lawsuit.Crime.values()[random.nextInt(Lawsuit.Crime.values().length)];
            Lawsuit lawsuit = new Lawsuit(regularPerson, crime);

            court.startLitigation(lawsuit, this, judge, lawyer);
        } catch (PersonNotFoundException e) {
            System.out.println(e + ".No Lawsuit was filed");
        }


    }

    private Lawyer getLawyer() throws PersonNotFoundException {
        Random random = new Random();
        List<Person> lawyers = people.stream()
                .filter(person -> person instanceof Lawyer)
                .collect(Collectors.toList());

        if (lawyers.size() > 0) {
            return (Lawyer) lawyers.get(random.nextInt(lawyers.size()));
        } else {
            throw new PersonNotFoundException(Person.Type.LAWYER);
        }
    }

    private Judge getJudge() throws PersonNotFoundException {
        Random random = new Random();
        List<Person> judges = people.stream()
                .filter(person -> person instanceof Judge)
                .collect(Collectors.toList());

        if (judges.size() > 0) {
            return (Judge) judges.get(random.nextInt(judges.size()));
        } else {
            throw new PersonNotFoundException(Person.Type.JUDGE);
        }
    }

    private RegularPerson getRegularPerson() throws PersonNotFoundException {
        List<Person> validPeople = people.stream()
                .filter(Person::canFileLawsuitAgainst)                  // People who can be sued
                .filter(person -> !incarceratedPeople.contains(person)) // People who haven't been incarcerated
                .collect(Collectors.toList());

        if (validPeople.size() > 0) {
            return (RegularPerson) validPeople.get(random.nextInt(validPeople.size()));
        } else {
            throw new PersonNotFoundException(Person.Type.REGULAR);
        }
    }


    public void addPersonToIncarceratedPeople(Person person) {
        incarceratedPeople.add(person);
    }

    public void addLitigationToArchive(Litigation litigation) {
        Person person = litigation.getPerson();
        if (!archive.containsKey(person)) {
            List<Litigation> litigations = new ArrayList<>();
            litigations.add(litigation);
            archive.put(person, litigations);
        } else {
            archive.get(person).add(litigation);
        }
    }

    public List<Person> getFreePeople() {
        return people.stream()
                .filter(Person::canFileLawsuitAgainst)                  // People who can be incarcerated
                .filter(person -> !incarceratedPeople.contains(person)) // People who haven't been incarcerated
                .collect(Collectors.toList());
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<Person> getIncarceratedPeople() {
        return incarceratedPeople;
    }

    public Map<Person, List<Litigation>> getArchive() {
        return archive;
    }
}

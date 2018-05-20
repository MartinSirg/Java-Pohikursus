package justice.court;

import justice.judgments.Conviction;
import justice.judgments.Judgement;
import justice.people.Judge;
import justice.people.Lawyer;
import justice.people.Person;
import justice.policeStation.PoliceStation;



public class Court {


    public void startLitigation(Lawsuit lawsuit, PoliceStation policeStation, Judge judge, Lawyer lawyer) {
        Person person = lawsuit.getPerson();
        Judgement judgement = judge.makeJudgement(lawsuit, lawyer);
        Litigation litigation = new Litigation(judgement, lawyer, judge, lawsuit);
        policeStation.addLitigationToArchive(litigation);

        if (judgement instanceof Conviction && ((Conviction) judgement).getJailTime() != 0) {
            policeStation.addPersonToIncarceratedPeople(person);
        }
    }
}

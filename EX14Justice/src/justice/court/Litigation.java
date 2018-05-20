package justice.court;

import justice.judgments.Judgement;
import justice.people.Judge;
import justice.people.Lawyer;
import justice.people.Person;

public class Litigation {
    private Judgement judgement;
    private Lawyer lawyer;
    private Judge judge;
    private Person person;
    private Lawsuit lawsuit;

    Litigation(Judgement judgement, Lawyer lawyer, Judge judge, Lawsuit lawsuit) {
        this.judgement = judgement;
        this.lawyer = lawyer;
        this.judge = judge;
        this.person = lawsuit.getPerson();
        this.lawsuit = lawsuit;
    }

    public Judgement getJudgement() {
        return judgement;
    }

    public Lawyer getLawyer() {
        return lawyer;
    }

    public Judge getJudge() {
        return judge;
    }

    public Person getPerson() {
        return person;
    }

    public Lawsuit getLawsuit() {
        return lawsuit;
    }
}

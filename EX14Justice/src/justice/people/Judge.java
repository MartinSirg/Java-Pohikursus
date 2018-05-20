package justice.people;

import justice.court.Lawsuit;
import justice.judgments.Judgement;
import justice.people.strategies.DefaultJudgeStrategy;
import justice.people.strategies.JudgeStrategy;
import justice.policeStation.PoliceStation;

public class Judge extends Person {
    private JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();

    Judge(int age, String fullName, PoliceStation policeStation) {
        super(age, fullName, policeStation);
    }

    public Judgement makeJudgement(Lawsuit lawsuit, Lawyer lawyer) {
        return judgeStrategy.makeDecision(lawsuit, lawyer);
    }

    public void setJudgeStrategy(JudgeStrategy judgeStrategy) {
        this.judgeStrategy = judgeStrategy;
    }

    @Override
    public boolean canFileLawsuitAgainst() {
        return false;
    }
}

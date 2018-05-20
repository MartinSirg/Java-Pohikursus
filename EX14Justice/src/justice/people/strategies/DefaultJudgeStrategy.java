package justice.people.strategies;

import justice.court.Lawsuit;
import justice.judgments.Conviction;
import justice.judgments.Judgement;
import justice.people.Lawyer;

public class DefaultJudgeStrategy implements JudgeStrategy {

    @Override // Finds everybody guilty with minimum punishments
    public Judgement makeDecision(Lawsuit lawsuit, Lawyer lawyer) {
        return new Conviction(lawsuit, lawsuit.getCrime().getMinFine(), lawsuit.getCrime().getMinJail());
    }
}

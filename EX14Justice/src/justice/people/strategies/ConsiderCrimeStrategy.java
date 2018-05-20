package justice.people.strategies;

import justice.court.Lawsuit;
import justice.judgments.Acquital;
import justice.judgments.Conviction;
import justice.judgments.Judgement;
import justice.people.Lawyer;

public class ConsiderCrimeStrategy implements JudgeStrategy {
    @Override
    public Judgement makeDecision(Lawsuit lawsuit, Lawyer lawyer) {
        switch (lawsuit.getCrime()) {
            case MURDER:
                return new Conviction(lawsuit, lawsuit.getCrime().getMaxFine(), lawsuit.getCrime().getMaxJail());
            case THEFT:
                return new Conviction(lawsuit, lawsuit.getCrime().getMinFine(), lawsuit.getCrime().getMaxJail());
            case VIOLENCE:
                return new Conviction(lawsuit, lawsuit.getCrime().getMaxFine(), lawsuit.getCrime().getMinJail());
            case SCARING:
                return new Acquital(lawsuit);
            default:
                return new Acquital(lawsuit);
        }
    }
}

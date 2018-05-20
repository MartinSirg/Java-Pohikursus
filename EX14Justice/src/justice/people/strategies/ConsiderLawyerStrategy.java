package justice.people.strategies;

import justice.court.Lawsuit;
import justice.judgments.Acquital;
import justice.judgments.Conviction;
import justice.judgments.Judgement;
import justice.people.Lawyer;

public class ConsiderLawyerStrategy implements JudgeStrategy {
    @Override
    public Judgement makeDecision(Lawsuit lawsuit, Lawyer lawyer) {
        if (lawyer.getWinningPercentage() > 51) return new Acquital(lawsuit);

        if (lawyer.getWinningPercentage() < 10) {//Max punishment
            return new Conviction(lawsuit, lawsuit.getCrime().getMaxFine(), lawsuit.getCrime().getMaxJail());
        } else if (lawyer.getWinningPercentage() < 40) { //Medium fine, min jail
            int mediumFine = (lawsuit.getCrime().getMaxFine() - lawsuit.getCrime().getMinFine()) / 2;
            return new Conviction(lawsuit, mediumFine, lawsuit.getCrime().getMinJail());
        } else { // min fine, jail
            return new Conviction(lawsuit, lawsuit.getCrime().getMinFine(), lawsuit.getCrime().getMinJail());
        }
    }
}

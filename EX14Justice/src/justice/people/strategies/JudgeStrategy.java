package justice.people.strategies;

import justice.court.Lawsuit;
import justice.judgments.Judgement;
import justice.people.Lawyer;

public interface JudgeStrategy {
    Judgement makeDecision(Lawsuit lawsuit, Lawyer lawyer);
}

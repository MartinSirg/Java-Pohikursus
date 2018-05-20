package justice.judgments;

import justice.court.Lawsuit;

public class Conviction extends Judgement {
    private int fine;
    private int jailTime;

    public Conviction(Lawsuit lawsuit, int fine, int jailtime) {
        super(lawsuit);
        this.fine = fine;
        this.jailTime = jailtime;
    }

    public int getFine() {
        return fine;
    }

    public int getJailTime() {
        return jailTime;
    }
}

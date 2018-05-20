package justice.judgments;

import justice.court.Lawsuit;

public abstract class Judgement {
    private Lawsuit lawsuit;

    Judgement(Lawsuit lawsuit) {
        this.lawsuit = lawsuit;
    }

    public Lawsuit getLawsuit() {
        return lawsuit;
    }
}

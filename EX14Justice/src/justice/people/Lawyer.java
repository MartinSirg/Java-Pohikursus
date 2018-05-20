package justice.people;

import justice.policeStation.PoliceStation;

public class Lawyer extends Person {
    private int lawsuitsParticipated = 0;
    private int lawsuitsWon = 0;

    Lawyer(int age, String fullName, PoliceStation policeStation) {
        super(age, fullName, policeStation);
    }

    public void updateLawsuitCounts(boolean won) {
        if (won) lawsuitsWon++;
        lawsuitsParticipated++;
    }

    public int getLawsuitsParticipated() {
        return lawsuitsParticipated;
    }

    public int getLawsuitsWon() {
        return lawsuitsWon;
    }

    public double getWinningPercentage() {
        return (1.0 * lawsuitsWon / lawsuitsParticipated) * 100;
    }

    @Override
    public boolean canFileLawsuitAgainst() {
        return false;
    }
}

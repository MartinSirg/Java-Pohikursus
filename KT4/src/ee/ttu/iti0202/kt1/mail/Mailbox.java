package ee.ttu.iti0202.kt1.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Mailbox {

    private int id;
    private List<Letter> normalLetters = new ArrayList<>(), spam = new ArrayList<>();

    public Mailbox(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Letter> getNormalLetters() {
        return normalLetters;
    }

    public List<Letter> getSpam() {
        return spam;
    }

    public void receiveLetter(Letter letter) {
        if (letter.getDestinationMailboxID() == id) normalLetters.add(letter);
        spam.add(letter);
    }

    public Optional<Letter> findLetterByTitle(String title) {
        title = title.toLowerCase();
        for (Letter letter: normalLetters) {
            if (letter.getTitle().toLowerCase().equals(title)) return Optional.of(letter);
        }
        for (Letter letter: spam) {
            if (letter.getTitle().toLowerCase().equals(title)) return Optional.of(letter);
        }
        return Optional.empty();
    }
}

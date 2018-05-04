package ee.ttu.iti0202.kt4.mail;

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
        if (spam.contains(letter) || normalLetters.contains(letter)) return;
        if (letter.getDestinationMailboxID() == id) {
            normalLetters.add(letter);
        } else {
            spam.add(letter);
        }
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

    public static void main(String[] args) {
        Letter normalLetter = new Letter(12, "From grandma", "Dear ..., I hope your test will be fine.");
        Letter normalLetter2 = new Letter(12, "Warning", "Don't forget to fix that bug or our boss will be angry...");
        Letter spam = new Letter(9999, "Small pillows - 50% OFF!!!", "Don't miss your change and buy a lot of small pillows!!!");

        Mailbox mailbox = new Mailbox(12);

        mailbox.receiveLetter(normalLetter);
        mailbox.receiveLetter(normalLetter2);
        mailbox.receiveLetter(spam);

        List<Letter> normalLetters = mailbox.getNormalLetters();
        List<Letter> spamLetters = mailbox.getSpam();

        System.out.println(normalLetters.size()); // 2
        System.out.println(spamLetters.size()); // 1

        System.out.println(normalLetters.contains(normalLetter)); // true
        System.out.println(normalLetters.contains(normalLetter2)); // true
        System.out.println(normalLetters.contains(spam)); // false
        System.out.println(spamLetters.contains(spam)); // true

// Override toString to see results
        System.out.println(mailbox.findLetterByTitle("From grandma")); // Optional[Letter{mailBoxID=12, title='From grandma'}]
        System.out.println(mailbox.findLetterByTitle("abc")); // Optional.empty
        System.out.println(mailbox.findLetterByTitle("Small pillows - 50% OFF!!")); // Optional.empty
        System.out.println(mailbox.findLetterByTitle("small PILLOWS - 50% off!!!")); // Optional[Letter{mailBoxID=9999, title='Small pillows - 50% OFF!!!'}]
    }
}

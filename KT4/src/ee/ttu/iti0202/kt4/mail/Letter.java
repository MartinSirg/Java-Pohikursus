package ee.ttu.iti0202.kt4.mail;

public class Letter {

    private String title;
    private int mailBoxId;
    private String content;

    public Letter(int mailBoxID, String title, String content) {
        this.mailBoxId = mailBoxID;
        this.title = title;
        this.content = content;
    }

    public int getDestinationMailboxID() {
        return mailBoxId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

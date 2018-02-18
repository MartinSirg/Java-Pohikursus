package ee.ttu.iti0202.bookshelf;

public class Book {
    public static int idCounter = 0;
    public static int getAndIncrementNextId() {
        return Book.idCounter++;
    }

    private String title;
    private String author;
    private int yearOfPublishing;
    private int price;
    private Person owner;
    private int id;

    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
        this.id = Book.getAndIncrementNextId();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public Person getOwner() {
        return owner;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public boolean buy(Person buyer) {
        if (buyer == null && owner == null) {
            return false;
        } else if (buyer ==  null) {
            owner.setMoney(owner.getMoney() + price);
            setOwner(null);
            return true;
        } else if (owner == null && buyer.getMoney() >= price) {
            buyer.setMoney(buyer.getMoney() - price);
            setOwner(buyer);
            return true;
        } else if (buyer.getMoney() >= price) {
            buyer.setMoney(buyer.getMoney() - price);
            owner.setMoney(owner.getMoney() + price);
            setOwner(buyer);
            return true;
        } else {
            return false;
        }
    }

}

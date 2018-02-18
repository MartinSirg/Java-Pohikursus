package ee.ttu.iti0202.bookshelf;


public class Person {
    private String name;
    private int money;

    public Person(String name, int money) {
        this.money = money;
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean buyBook(Book book) {
        if (book == null) return false;
        if (book.getOwner() != null) return false;
        return book.buy(this);
    }

    public boolean sellBook(Book book) {
        if (book == null)  return false;
        if (book.getOwner() != this) return false;
        return book.buy(null);
    }
}

package ee.ttu.iti0202.json;

import com.google.gson.annotations.Expose;

public class Item {

    private String item_id;
    private String title;
    private double price;
    private int count;

    @Override
    public String toString() {
        return String.format("\nID: %s, Title: %s, Price: %.2f, Count: %d", item_id, title, price, count);
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

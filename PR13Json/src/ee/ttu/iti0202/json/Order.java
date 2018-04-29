package ee.ttu.iti0202.json;

import java.util.List;
import com.google.gson.annotations.*;

public class Order {

    private int order_number;
    private String customer;
    private double total_price;
    private List<Item> items;

    @Override
    public String toString() {
        return String.format("Order number: %d \n Customer: %s  \n Items:", order_number, customer) + items;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

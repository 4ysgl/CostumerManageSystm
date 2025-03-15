package entity;

import java.time.LocalDate;

public class Card {
    private int id;
    private int productId;
    private int customerId;
    private String note;
    private int price;
    private LocalDate date;
    private  Product product;
    private Customer customer;

    public Card() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", productId=" + productId +
                ", customerId=" + customerId +
                ", note='" + note +
                ", price=" + price +
                ", date=" + date +
                ", product=" + product +
                ", customer=" + customer +
                '}';
    }
}

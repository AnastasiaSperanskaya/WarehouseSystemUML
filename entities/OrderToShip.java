package entities;

import java.util.ArrayList;
import java.util.List;

public class OrderToShip {
    private List<ProductUnit> products = new ArrayList<ProductUnit>();
    private Client customer;
    private int orderID;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public List<ProductUnit> getProducts() {
        return products;
    }

    public void setProducts(List<ProductUnit> products) {
        this.products = products;
    }

    public Client getCustomer() {
        return customer;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    public void setProduct(ProductUnit product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "OrderToShip{" +
                "products=" + products +
                ", customer=" + customer +
                ", orderID=" + orderID +
                '}';
    }
}

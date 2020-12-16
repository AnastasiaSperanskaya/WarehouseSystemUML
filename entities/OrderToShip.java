package entities;

import java.util.List;

public class OrderToShip {
    private List<ProductUnit> products;
    private Client customer;

    public List<ProductUnit> getProducts() {
        return products;
    }

    public void setProducts(List<ProductUnit> products) {
        this.products = products;
    }

    public Client getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "OrderToShip{" +
                "products=" + products +
                ", customer=" + customer +
                '}';
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    public void setProduct(ProductUnit product) {
        this.products.add(product);
    }
}

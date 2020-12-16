package entities;

import java.util.ArrayList;
import java.util.List;

public class OrderToArrive {
    private List<ProductUnit> products = new ArrayList<ProductUnit>();
    private Client provider;
    private int orderID;

    public List<ProductUnit> getProducts() {
        return products;
    }

    public void setProducts(List<ProductUnit> products) {
        this.products = products;
    }

    public Client getProvider() {
        return provider;
    }

    public void setProvider(Client provider) {
        this.provider = provider;
    }

    public void setProduct(ProductUnit product) {
        this.products.add(product);
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "OrderToArrive{" +
                "products=" + products +
                ", provider=" + provider +
                ", orderID=" + orderID +
                '}';
    }
}

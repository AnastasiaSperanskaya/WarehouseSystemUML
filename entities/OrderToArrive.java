package entities;

import java.util.List;

public class OrderToArrive {
    private List<ProductUnit> products;
    private Client provider;

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

    @Override
    public String toString() {
        return "OrderToArrive{" +
                "products=" + products +
                ", provider=" + provider +
                '}';
    }
}

package entities;

public class ProductUnit {
    private int productID;
    private int placeID;
    private int height_cm;
    private int width_cm;
    private int length_cm;
    public String status = "waiting for arrival"; // waiting for arrival / arrived / waiting for shipment / shipped

    @Override
    public String toString() {
        return "ProductUnit{" +
                "productID=" + productID +
                ", placeID=" + placeID +
                ", height_cm=" + height_cm +
                ", width_cm=" + width_cm +
                ", length_cm=" + length_cm +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductUnit() { }

    public int getProductID() {
        return this.productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getPlaceID() { return this.placeID; }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public int getHeight_cm() { return height_cm; }

    public void setHeight_cm(int height_cm) { this.height_cm = height_cm; }

    public int getWidth_cm() { return width_cm; }

    public void setWidth_cm(int width_cm) { this.width_cm = width_cm; }

    public int getLength_cm() { return length_cm; }

    public void setLength_cm(int length_cm) { this.length_cm = length_cm; }
}

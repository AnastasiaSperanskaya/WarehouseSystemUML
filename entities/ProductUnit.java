package entities;

public class ProductUnit {
    private int productID;
    private int placeID;
    private Long barCode;
    private int height_cm;
    private int width_cm;
    private int length_cm;

    @Override
    public String toString() {
        return "ProductUnit{" +
                "productID=" + productID +
                ", placeID=" + placeID +
                ", barCode=" + barCode +
                ", height_cm=" + height_cm +
                ", width_cm=" + width_cm +
                ", length_cm=" + length_cm +
                ", amount_kg=" + amount_kg +
                ", isUnique=" + isUnique +
                '}';
    }

    private double amount_kg;
    private boolean isUnique;
    //private Provider provider;
    //public String type; //type can be - ship / arrive / none

    public ProductUnit() { }

    public void changeAmount(String sign, double amount) {
        if(sign.equals("-")) this.amount_kg = this.amount_kg - amount;
        else this.amount_kg = this.amount_kg + amount;
    }

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

    public double getAmount_kg() { return amount_kg; }

    public void setAmount_kg(double amount_kg) { this.amount_kg = amount_kg; }

    public boolean isUnique() { return isUnique; }

    public void setUnique(boolean unique) { isUnique = unique; }

    public Long getBarCode() { return barCode; }

    public void setBarCode(Long barCode) { this.barCode = barCode; }
}

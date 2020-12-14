package entities;

public class ProductUnit {
    public static int productID;
    public static int placeID;
    public static Long barCode;
    public static Long height_cm;
    public static Long width_cm;
    public static Long length_cm;
    public static Provider provider;
    public static double amount_kg;
    public static boolean isUnique;

    public static void changeAmount(String sign, double amount) {
        if(sign.equals("-")) ProductUnit.amount_kg = ProductUnit.amount_kg - amount;
        else ProductUnit.amount_kg = ProductUnit.amount_kg + amount;
    }

    public static int getProductID() {
        return productID;
    }

    public static void setProductID(int productID) {
        ProductUnit.productID = productID;
    }

    public static int getPlaceID() {
        return placeID;
    }

    public static void setPlaceID(int placeID) {
        ProductUnit.placeID = placeID;
    }

    public static Long getBarCode() {
        return barCode;
    }

    public static void setBarCode(Long barCode) {
        ProductUnit.barCode = barCode;
    }

    public static Long getHeight_cm() {
        return height_cm;
    }

    public static void setHeight_cm(Long height_cm) {
        ProductUnit.height_cm = height_cm;
    }

    public static Long getWidth_cm() {
        return width_cm;
    }

    public static void setWidth_cm(Long width_cm) {
        ProductUnit.width_cm = width_cm;
    }

    public static Long getLength_cm() {
        return length_cm;
    }

    public static void setLength_cm(Long length_cm) {
        ProductUnit.length_cm = length_cm;
    }

    public static Provider getProvider() {
        return provider;
    }

    public static void setProvider(Provider provider) {
        ProductUnit.provider = provider;
    }

    public static double getAmount_kg() {
        return amount_kg;
    }

    public static void setAmount_kg(double amount_kg) {
        ProductUnit.amount_kg = amount_kg;
    }

    public static boolean isIsUnique() {
        return isUnique;
    }

    public static void setIsUnique(boolean isUnique) {
        ProductUnit.isUnique = isUnique;
    }


}

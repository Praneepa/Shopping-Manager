
// Abstract class representing a Product
public abstract class Product {
    private String productId;
    private String productName;
    private int availableItems;
    private double price;
    private String category;
    private int Qunatity;
    // Constructor
    public Product(String productId, String productName, int availableItems, double price,String category) {
        this.productId = productId;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
        this.category=category;
    }

    // Getters and setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAvailableItems() {
        return availableItems;
    }

    public String getCategory(){

        return category;
    }

    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return Qunatity;
    }

    public void setQuantity(int quantity) {
        this.Qunatity = quantity;
    }
}

// Electronics subclass
class Electronics extends Product {
    private String brand;
    private String warrantyPeriod;

    // Constructor
    public Electronics(String productId, String productName, int availableItems, double price, String brand, String warrantyPeriod) {
        super(productId, productName, availableItems, price,"Electronics");
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    // Getters and setters for Electronics
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }


}

// Clothing subclass
class Clothing extends Product {

    private String size;
    private String color;


    // Constructor
    public Clothing(String productId, String productName, int availableItems, double price, String size, String color) {
        super(productId, productName, availableItems, price,"Clothing");
        this.size = size;
        this.color = color;
    }

    // Getters and setters for Clothing
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }



}

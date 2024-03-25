import java.io.*;
import java.security.PublicKey;
import java.util.*;


// Interface for ShoppingManager
interface ShoppingManager {
    void displayMenu();
    void addProductToInventory(Product product);
    void removeProductFromInventory();
    void displayInventory();

    public void saveProductsToFile();
}


public class WestminsterShoppingManager implements ShoppingManager {
    private static final int MAX_PRODUCTS = 50;
    private final List<Product> inventory = new ArrayList<>();   //list for all products in system

    public WestminsterShoppingManager(){
        loadProductsFromFile();
    }

    @Override
    public void displayMenu() {
        System.out.println("----- Welcome to Shopping Manager Menu Console! ----- \n Select Option");
        System.out.println("1. Add a new product to the system");
        System.out.println("2. Delete a product from the system");
        System.out.println("3. Print the list of products in the system");
        System.out.println("4. Save products to a file");
        System.out.println("5. Exit");
        System.out.println("-------------------------------");
        menuManager();
    }

    public void menuManager() {
        while (true) {
            System.out.println("Choose Your Option");
            int choice=0;
            Scanner input = new Scanner(System.in);
            try{
                choice = input.nextInt();
            }
            catch (Exception e){
                input.nextLine();
            }

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("Choose product category");
                        System.out.println("1-Electronics\n2-Clothing");
                        int category = 0;
                        try {
                            category = input.nextInt();
                        } catch (Exception e) {
                            System.out.println("Input a Valid number");
                            input.nextLine();
                        }

                        if (category == 1) {  //adding an electronics product

                            System.out.println("Enter product ID: ");
                            String pId = input.next();
                            for (Product product : inventory) {
                                if (pId.equals(product.getProductId())) {
                                    System.out.println("Product ID Exists\n");
                                    displayMenu();
                                }
                            }
                            System.out.println("Enter product name: ");
                            String pName = input.next();
                            System.out.println("Enter product price (LKR): ");
                            double pPrice=0;
                            try{
                                pPrice = input.nextDouble();
                            }
                            catch (Exception e){
                                System.out.println("Please enter a double value\n");
                                displayMenu();
                            }
                            System.out.println("Enter number of available items: ");
                            int pAvailable=0;
                            try{
                                 pAvailable = input.nextInt();
                            }
                            catch (Exception e){
                                System.out.println("Please enter a valid number\n");
                                displayMenu();
                            }
                            System.out.println("Enter brand: ");
                            String pBrand = input.next();
                            System.out.println("Enter warranty period (in months): ");
                            String pWarranty = input.next();

                            //new product electronic created
                            Product electronic = new Electronics(pId, pName, pAvailable, pPrice, pBrand, pWarranty);
                            //call method that adds to inventory
                            addProductToInventory(electronic);
                            //displayInventory();  //display new inventory

                            System.out.println("Enter 1 to continue adding products or 2 to return to menu");
                            int response = input.nextInt();

                            if (response == 1) {
                                continue;
                            } else {
                                displayMenu();

                            }


                        }
                        else if (category == 2) {  //adding a clothing item
                            System.out.println("Enter product ID: ");
                            String pId = input.next();
                            for (Product product : inventory) {
                                if (pId.equals(product.getProductId())) {
                                    System.out.println("Product ID Exists\n");
                                    displayMenu();
                                }
                            }

                            System.out.println("Enter product name: ");
                            String pName = input.next();
                            System.out.println("Enter product price (LKR): ");
                            double pPrice=0;
                            try{
                                pPrice = input.nextDouble();
                            }
                            catch (Exception e){
                                System.out.println("Please enter a double value\n");
                                displayMenu();
                            }
                            System.out.println("Enter number of available items: ");
                            int pAvailable=0;
                            try{
                                pAvailable = input.nextInt();
                            }
                            catch (Exception e){
                                System.out.println("Please enter a valid number\n");
                                displayMenu();
                            }
                            System.out.println("Enter size (S/M/L/XL etc): "); // S,M,L,XL, etc
                            String pSize = input.next();
                            System.out.println("Enter colour: ");
                            String pColour = input.next();

                            //new clothing product created
                            Product clothing = new Clothing(pId, pName, pAvailable, pPrice, pSize, pColour);
                            //call method that adds to inventory
                            addProductToInventory(clothing);
                            //displayInventory();     //display new inventory

                            System.out.println("Enter 1 to continue adding products or 2 to return to menu");
                            int response = input.nextInt();

                            if (response == 1) {
                                continue;
                            }
                            else {
                                System.out.println("Please Enter a Valid Choice");
                                displayMenu();
                            }
                        }

                    }

                case 2:
                    //method to remove from inventory
                    removeProductFromInventory();

                case 3:
                    // Print the list of products in the system
                    displayInventory();

                    break;
                case 4:
                    saveProductsToFile();  //save to file
                    System.out.println("Products Saved Successfully");
                    displayMenu();
                    break;
                case 5:
                    //exit system
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        }

    }

    //method to add product to inventory
    @Override
    public void addProductToInventory(Product product) {
        if (inventory.size() < MAX_PRODUCTS) {
            inventory.add(product);
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Cannot add more products. Maximum limit reached.");
        }
    }


    //method that removes from inventory
    @Override
    public void removeProductFromInventory() {
        Scanner input = new Scanner(System.in);

        while (true) {

            displayInventory(); //to make it easier for user to see the product IDs
            System.out.println("Enter product ID of the product that needs to be removed: ");

            String removeId = input.next();
            // Find the product with the specified ID
            Product productToRemove = null;
            for (Product product : inventory) {
                if (Objects.equals(product.getProductId(), removeId)) {
                    productToRemove = product;
                    break;
                }
            }


            // Display details of the product before removing it
            if (productToRemove != null) {
                System.out.println("Details of the product with ID " + removeId + ":");
                System.out.println(productToRemove.getClass() + "\nProduct name: " + productToRemove.getProductName() + "\nPrice: " + productToRemove.getPrice());

                // Remove the product from the ArrayList
                inventory.remove(productToRemove);

                // Print the number of products left in ArrayList

                System.out.println("Number of products left in system: " + inventory.size());

            } else {
                System.out.println("Product with ID " + removeId + " not found in the inventory.");
            }

            System.out.println("Enter 1 to continue removing products or 2 to return to menu");
            int response = input.nextInt();

            if (response == 1) {
                continue;
            } else {
                displayMenu();
            }

            break;
        }



    }



//method to display products in inventory
    @Override
    public void displayInventory() {


        System.out.println("----- List of Products in the System -----");
        if (inventory.size() != 0) {
            // Sort the productList alphabetically based on product ID
            Collections.sort(inventory, Comparator.comparing(Product::getProductId));

            System.out.println("Products in the shopping cart:");
            System.out.println(
                    "Product ID\t\tCategory\t\tProduct Name\t\tAvailable Items\t\tPrice\t\tBrand/Size\t\tWarrantyPeriod/Color\n");
            for (Product product : inventory) {
                //format to a single string for product details (each row)
                System.out.printf("%-16s%-16s%-18s%-22d%-12.2f",
                        product.getProductId(),
                        product.getCategory(),
                        product.getProductName(),
                        product.getAvailableItems(),
                        product.getPrice());
                if (product instanceof Electronics) {
                    Electronics electronicsProduct = (Electronics) product;
                    System.out.println(
                            "\t" + electronicsProduct.getBrand() + "\t\t\t\t" + electronicsProduct.getWarrantyPeriod());
                }
                if (product instanceof Clothing) {
                    Clothing clothingProduct = (Clothing) product;
                    System.out.println("\t" + clothingProduct.getSize() + "\t\t\t\t" + clothingProduct.getColor());
                }
            }
            return;
        } else {
            System.out.println("No Items Available\n");
            return;
        }

    }

    // Save products to a file
    @Override
    public void saveProductsToFile() {
        if (inventory.size() != 0) {
            try {
                FileWriter pointer = new FileWriter("C:\\IIT\\JAVA\\w1953808_20221106_OOP_CW2\\src\\ProductLists.txt");

                for (Product product : inventory) {
                    pointer.write(
                            product.getProductId() + "\t" + product.getCategory() + "\t" + product.getProductName()
                                    + "\t" + product.getAvailableItems() + "\t" + product.getPrice() + "\t");
                    if (product instanceof Electronics) {
                        Electronics electronicsProduct = (Electronics) product;
                        pointer.write(electronicsProduct.getBrand() + "\t"
                                + electronicsProduct.getWarrantyPeriod());
                    }
                    if (product instanceof Clothing) {
                        Clothing clothingProduct = (Clothing) product;
                        pointer.write(clothingProduct.getSize() + "\t" + clothingProduct.getColor());
                    }
                    pointer.write("\n");

                }
                pointer.close();
            } catch (IOException e) {
                System.out.println("Error");
            }
        } else {
            System.out.println("No Items Available\n");
        }
    }

    //method to return inventory
    public List<Product> getInventory(){
        return  inventory;
    }
    // Load products from a file
    public void loadProductsFromFile() {
        try {

            // Getting the Product List Saved On the txt file
            FileReader pointer = new FileReader(
                    "C:\\IIT\\JAVA\\w1953808_20221106_OOP_CW2\\src\\ProductLists.txt");

            Scanner readfile = new Scanner(pointer);

            // Inserting the products to the Product List
            while (readfile.hasNextLine()) {
                String data = readfile.nextLine();
                String[] values = data.split("\t");
                if (values[1].equals("Clothing")) {

                    Product newProduct = new Clothing(values[0], values[2], Integer.parseInt(values[3]),
                            Double.parseDouble(values[4]),
                            values[5], values[6]);
                    inventory.add(newProduct);

                } else { //if electronics
                    Product newProduct2 = new Electronics(values[0], values[2], Integer.parseInt(values[3]),
                            Double.parseDouble(values[4]),
                            values[5], values[6]);
                    inventory.add(newProduct2);
                }

            }
        } catch (IOException e) {
            System.out.println("Error");
        }


    }
}

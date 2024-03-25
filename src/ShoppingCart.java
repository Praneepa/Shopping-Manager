
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

public class ShoppingCart {

    //list of cart products
    private List<Product> products;
    private double FreshCustomerDiscount = 0;
    private double ThreeItems_Discount = 0;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    //reset cart method
    public void setCart() {
        getProducts().clear();
        clearDiscounts();
    }


    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {

        return products;
    }

    public void removeProduct(Product product) {

        products.remove(product);
    }


    //calculate total cost method
    public double calculateTotalCost() {
        double totalCost = 0;
        for (Product product : products) {
            if (product.getQuantity() > 0) {
                for (int i = 0; i < product.getQuantity(); i++) {
                    totalCost += product.getPrice();
                }
            } else {
                totalCost += product.getPrice();

            }

        }
        return totalCost;
    }


    // 3 of the same category discount calculation method
    public double hasAtLeastThreeItemsOfSameProduct() {
        boolean Y = false;
        for (Product product : products) {

            if (product.getQuantity() >= 3) {
                Y = true;
            }
        }
        if (Y) {
            ThreeItems_Discount = calculateTotalCost() * 0.2;
            return ThreeItems_Discount;
        }
        return 0;
    }

    //return discount of first time customer
    public double GetFreshCustomerDiscount() {
        return FreshCustomerDiscount;

    }

    //first customer discount method
    public double SetFreshCustomerDiscount() {
        FreshCustomerDiscount = calculateTotalCost() * 0.1;
        return FreshCustomerDiscount;

    }

    //method to calculate final price after discounts
    public double calculateFinalTotalCost() {
        return (calculateTotalCost() - FreshCustomerDiscount - ThreeItems_Discount);
    }

    //method to reset discount
    public void clearDiscounts() {
        FreshCustomerDiscount = 0;
        ThreeItems_Discount = 0;
    }
    public void initializeShoppingCart(boolean T) {

        //list for column names
        String[] columnNames = { "Product Details", "Quantity", "Price" };
        String[][] data = new String[products.size()][3];


        //formatting a single string for the product details column as formatted data

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            String formattedData;

            if (product.getCategory().equals("Clothing")) {
                formattedData = String.format("%-15s%-25s%-10s%-10s",
                        product.getProductId() + "\t",
                        product.getProductName() + "\t",
                        ((Clothing) product).getSize() + "\t",
                        ((Clothing) product).getColor());
            } else {
                formattedData = String.format("%-15s%-25s%-20s%-20s",
                        product.getProductId() + "\t",
                        product.getProductName() + "\t",
                        ((Electronics) product).getBrand() + "\t",
                        ((Electronics) product).getWarrantyPeriod());
            }

            //assigning the values to the columns
            data[i][0] = formattedData;
            data[i][1] = String.valueOf(product.getQuantity());
            data[i][2] = String.valueOf(product.getPrice());
        }
        if (T) {
            this.SetFreshCustomerDiscount();
        }

        //creating shopping cart table and passing the list of column names
        JTable shoppingTable = new JTable(data, columnNames);
        //header and size adjust
        JTableHeader header = shoppingTable.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 45));

        shoppingTable.setPreferredScrollableViewportSize(new Dimension(350, 200));
        int rowHeight = 60;
        shoppingTable.setRowHeight(rowHeight);

        //scroll pane for table
        JScrollPane scrollPane = new JScrollPane(shoppingTable);
        int topPadding = 60;
        scrollPane.setBorder(new EmptyBorder(topPadding, topPadding, 0, topPadding));


        //bottom panel of the shopping cart

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(4, 2, 5, 5));
        bottom.setPreferredSize(new Dimension(800, 250));

        int topPadding1 = 50;
        bottom.setBorder(new EmptyBorder(topPadding1, topPadding1, topPadding1, topPadding1));

        //panel inside the bottom panel for total price and value
        JPanel bottom1 = new JPanel();
        //layout as a grid with 2 columns and 1 row
        bottom1.setLayout(new GridLayout(1, 2, 5, 5));
        //label for total price in first column
        JLabel bottom1Text = new JLabel("Total");
        //label for value of total in 2nd column
        JLabel bottom1Value = new JLabel(String.valueOf(calculateTotalCost()));

        //adding the 2 labels to the panel bottom1
        bottom1.add(bottom1Text);
        bottom1.add(bottom1Value);

        //another panel for first purchase discount and value
        JPanel bottom2 = new JPanel();
        bottom2.setLayout(new GridLayout(1, 2, 5, 5));

        //labels for the columns in the grid
        JLabel bottom2Text = new JLabel("First Purchase Discount)(10%)");
        JLabel bottom2Value = new JLabel(String.valueOf(GetFreshCustomerDiscount()));

        //adding the labels to the bottom2 panel
        bottom2.add(bottom2Text);
        bottom2.add(bottom2Value);

        //another panel for the 20% discount
        JPanel bottom3 = new JPanel();
        bottom3.setLayout(new GridLayout(1, 2, 5, 5));

        //labels for the columns
        JLabel bottom3Text = new JLabel("Three Items in Same Category Discount(20%)");
        JLabel bottom3Value = new JLabel(String.valueOf(hasAtLeastThreeItemsOfSameProduct()));

        //adding the columns to the bottom3 panel
        bottom3.add(bottom3Text);
        bottom3.add(bottom3Value);

        //another panel for final price and value
        JPanel bottom4 = new JPanel();
        bottom4.setLayout(new GridLayout(1, 2, 5, 5));

        //labels for the columns
        JLabel bottom4Text = new JLabel("Final Total");
        JLabel bottom4Value = new JLabel(String.valueOf(calculateFinalTotalCost()));

        //adding the labels to bottom 4 panel
        bottom4.add(bottom4Text);
        bottom4.add(bottom4Value);

        //adding all 4 bottom panels to the main bottom panel
        bottom.add(bottom1);
        bottom.add(bottom2);
        bottom.add(bottom3);
        bottom.add(bottom4);

        //creating main panel
        JPanel mainPanel1 = new JPanel();

        //set layout of main panel
        mainPanel1.setLayout(new BorderLayout());
        mainPanel1.setBackground(new Color(128, 128, 128));

        //adding shopping cart table scroll pane to main panel
        mainPanel1.add(scrollPane, BorderLayout.CENTER);
        //adding the bottom panel to main panel
        mainPanel1.add(bottom, BorderLayout.SOUTH);

        //shopping cart frame creation

        JFrame shoppingCartFrame = new JFrame("Shopping Cart");
        shoppingCartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        shoppingCartFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setCart();

            }
        });

        //adding the panel to the frame
        shoppingCartFrame.getContentPane().add(mainPanel1); // Use getContentPane() here
        //setting the frame size,location and visibility
        shoppingCartFrame.setSize(800, 600);
        shoppingCartFrame.setLocationRelativeTo(null);
        shoppingCartFrame.setVisible(true);

    }
    public boolean isEmpty() {
        return products.isEmpty();
    } //returns true of empty
}
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class HomeGui extends JFrame{
    private JComboBox<String> categoryComboBox;
    private JTable productTable;
    private List<Product> productList;

    private DefaultTableModel tableModel;
    private JTextArea productDetailsTextArea;

    public void HomeGui(List<Product> productList, User user){

        //label for the ID. The value will be set when user clicks on the product
        JLabel panelCont1 = new JLabel();
        //label for the product category. The value will be set when user clicks on the product
        JLabel panelCont2 = new JLabel();
        //label for the product name. The value will be set when user clicks on the product
        JLabel panelCont3 = new JLabel();
        //label for the size/warranty. The value will be set when user clicks on the product
        JLabel panelCont4 = new JLabel();
        //label for the color/brand. The value will be set when user clicks on the product
        JLabel panelCont5 = new JLabel();
        //label for the available items. The value will be set when user clicks on the product
        JLabel panelCont6 = new JLabel();

        //labels for the details section below product table
        //JLabel Product_4 = new JLabel("Size / Warranty Period (In months)");
        JLabel Product_5 = new JLabel("Color / Brand");


        //add to cart button
        JButton AddProduct = new JButton("Add to Shopping Cart");



        //list for combo box options
        String[] categories = { "All", "Electronics", "Clothing" };
        //creating combo box
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);
        //label for selecting category in combo box
        JLabel Text = new JLabel("Select Product Category");
        Text.setFont(new Font("Arial", Font.PLAIN, 15));


        //--------------------------Products Table---------------------------

        // list for 6 columns of the product table
        Object[][] data = new Object[productList.size()][6];
        //column name list for product table
        String[] columnNames = { "Product ID", "Name", "Category", "Available Items", "Price (LKR)", "Info" };
        //creating j table for product table
        JTable productTable = new JTable(data, columnNames);


        //----------------------When user selects on combo box------------------------------

        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String selectedCategory = (String) categoryComboBox.getSelectedItem();
                    for (int i = 0; i < data.length; i++) {
                        for (int j = 0; j < data[i].length; j++) {
                            data[i][j] = null;
                        }
                    }
                    if ("All".equals(selectedCategory)) {

                        for (int i = 0; i < productList.size(); i++) {
                            Product product = productList.get(i);
                            data[i][0] = product.getProductId();
                            data[i][1] = product.getProductName();
                            data[i][2] = product.getCategory();
                            data[i][3] = product.getAvailableItems();
                            data[i][4] = product.getPrice();

                            if (product instanceof Electronics) {
                                Electronics electronicsProduct = (Electronics) product;
                                data[i][5] = electronicsProduct.getBrand() + " " + electronicsProduct.getWarrantyPeriod();
                            } else if (product instanceof Clothing) {
                                Clothing clothingProduct = (Clothing) product;
                                data[i][5] = clothingProduct.getSize() + " " + clothingProduct.getColor();
                            }
                        }
                    }
                    if ("Electronics".equals(selectedCategory)) {
                        int j = 0;

                        for (int i = 0; i < productList.size(); i++) {
                            Product product = productList.get(i);
                            if ("Electronics".equals(product.getCategory())) {
                                data[j][0] = product.getProductId();
                                data[j][1] = product.getProductName();
                                data[j][2] = product.getCategory();
                                data[j][3] = product.getAvailableItems();
                                data[j][4] = product.getPrice();

                                if (product instanceof Electronics) {
                                    Electronics electronicsProduct = (Electronics) product;
                                    data[j][5] = electronicsProduct.getBrand() + " "
                                            + electronicsProduct.getWarrantyPeriod();
                                } else if (product instanceof Clothing) {
                                    Clothing clothingProduct = (Clothing) product;
                                    data[j][5] = clothingProduct.getSize() + " " + clothingProduct.getColor();
                                }
                                j++;
                            }
                        }
                        j = 0;

                    }
                    if ("Clothing".equals(selectedCategory)) {
                        int j = 0;
                        for (int i = 0; i < productList.size(); i++) {
                            Product product = productList.get(i);
                            if ("Clothing".equals(product.getCategory())) {
                                data[j][0] = product.getProductId();
                                data[j][1] = product.getProductName();
                                data[j][2] = product.getCategory();
                                data[j][3] = product.getAvailableItems();
                                data[j][4] = product.getPrice();

                                if (product instanceof Electronics) {
                                    Electronics electronicsProduct = (Electronics) product;
                                    data[j][5] = electronicsProduct.getBrand() + " "
                                            + electronicsProduct.getWarrantyPeriod();
                                } else if (product instanceof Clothing) {
                                    Clothing clothingProduct = (Clothing) product;
                                    data[j][5] = clothingProduct.getSize() + " " + clothingProduct.getColor();
                                }
                                j++;

                            }

                        }
                        j = 0;
                    }
                    ((AbstractTableModel) productTable.getModel()).fireTableDataChanged();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });


        //creating shopping cart button
        JButton shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.setPreferredSize(new Dimension(150, 35));


        //------------------When user clicks shopping cart button------------------------

        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!user.ShoppingCart.isEmpty()) {

                    if (user.getFreshCustomer()) {
                        user.setFreshCustomer(false);
                        user.ShoppingCart.initializeShoppingCart(true);
                    } else {
                        user.ShoppingCart.initializeShoppingCart(false);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(HomeGui.this, "Shopping Cart is Empty");
                }

            }

        });



        //-----------Top Panel of frame (label,combo box and cart button)--------------------------

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(1, 3, 5, 5));
        Dimension preferredSize = new Dimension(600, 150);
        formPanel.setPreferredSize(preferredSize);
        int topPadding = 60;
        formPanel.setBorder(new EmptyBorder(topPadding, 0, 0, 0));

        JPanel formPanel1 = new JPanel();
        formPanel1.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JPanel formPanel2 = new JPanel();
        formPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel formPanel3 = new JPanel();
        formPanel3.setLayout(new FlowLayout(FlowLayout.CENTER));


        //add lable for select category to formpanel1
        formPanel1.add(Text);
        //add combo box to form panel2
        formPanel2.add(categoryComboBox);
        //add shopping cart button to formpanel3
        formPanel3.add(shoppingCartButton);

        //adding the sub panels to the main top panel
        formPanel.add(formPanel1);
        formPanel.add(formPanel2);
        formPanel.add(formPanel3);

        /*---------------- Center Part (Products table)---------------- */

        //sort products alphabetically
        Collections.sort(productList, Comparator.comparing(Product::getProductId));


        //populating the products table
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            data[i][0] = product.getProductId();
            data[i][1] = product.getProductName();
            data[i][2] = product.getCategory();
            data[i][3] = product.getAvailableItems();
            data[i][4] = product.getPrice();

            if (product instanceof Electronics) {
                Electronics electronicsProduct = (Electronics) product;
                data[i][5] = electronicsProduct.getBrand() + " " + electronicsProduct.getWarrantyPeriod();
            }
            if (product instanceof Clothing) {
                Clothing clothingProduct = (Clothing) product;
                data[i][5] = clothingProduct.getSize() + " " + clothingProduct.getColor();
            }
        }

        productTable.setPreferredScrollableViewportSize(new Dimension(350, 200));
        productTable.getColumnModel().getColumn(3).setCellRenderer(new CustomerTableCellReander.CustomerTableCellRenderer());
        int rowHeight = 20;
        productTable.setRowHeight(rowHeight);

        JScrollPane scrollPane = new JScrollPane(productTable);
        Color backgroundColor = Product_5.getBackground();
        scrollPane.setBorder(BorderFactory.createLineBorder(backgroundColor, 50));


        //display info of selected product

        productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // This is called when the selection changes
                    int selectedRow = productTable.getSelectedRow();
                    if (selectedRow != -1) {
                        Object[] selectedRowData = data[selectedRow];
                        for (Product product : productList) {
                            if (product.getProductId().equals(String.valueOf(selectedRowData[0]))) {
                                panelCont1.setText(product.getProductId());
                                panelCont2.setText(product.getCategory());
                                panelCont3.setText(product.getProductName());
                                if (product instanceof Electronics) {
                                    Electronics electronicsProduct = (Electronics) product;

                                    panelCont4.setText(String.valueOf(electronicsProduct.getWarrantyPeriod()));
                                    panelCont5.setText(electronicsProduct.getBrand());


                                } else if (product instanceof Clothing) {
                                    Clothing clothingProduct = (Clothing) product;

                                    panelCont4.setText(String.valueOf(clothingProduct.getSize()));
                                    panelCont5.setText(String.valueOf(clothingProduct.getColor()));

                                }
                                panelCont6.setText(String.valueOf(product.getAvailableItems()));
                                AddProduct.setEnabled(true);
                            }
                        }

                    }
                }
            }
        });

        getContentPane().add(scrollPane);

        /*----------------  Bottom panels of the frame ---------------- */

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1, 5, 5));
        buttonsPanel.setPreferredSize(new Dimension(400, 350));

        JPanel buttonsPanel1 = new JPanel();
        buttonsPanel1.setLayout(new GridLayout(7, 1, 5, 5));

        /*------------Panel for product ID display of selected item---------- */

        JPanel buttonPanen1Cont = new JPanel();
        buttonPanen1Cont.setLayout(new GridLayout(1, 2, 5, 5));

        JLabel Product_ID = new JLabel("Product ID");
        buttonPanen1Cont.add(Product_ID);
        buttonPanen1Cont.add(panelCont1);

        /*-----------Panel for product category display of selected item----------- */

        JPanel buttonPanen1Cont2 = new JPanel();
        buttonPanen1Cont2.setLayout(new GridLayout(1, 2, 5, 5));

        JLabel Product_Category = new JLabel("Product Category");
        buttonPanen1Cont2.add(Product_Category);
        buttonPanen1Cont2.add(panelCont2);

        /*-----------Panel for product name display of selected item----------- */

        JPanel buttonPanen1Cont3 = new JPanel();
        buttonPanen1Cont3.setLayout(new GridLayout(1, 2, 5, 5));

        JLabel Product_Name = new JLabel("Product Name");
        buttonPanen1Cont3.add(Product_Name);
        buttonPanen1Cont3.add(panelCont3);

        /*-----------Panel for size/warranty period display of selected item----------- */

        JPanel buttonPanen1Cont4 = new JPanel();
        buttonPanen1Cont4.setLayout(new GridLayout(1, 2, 5, 5));
        JLabel Product_4 = new JLabel("Size / Warranty Period (In months)");
        buttonPanen1Cont4.add(Product_4);
        buttonPanen1Cont4.add(panelCont4);

        /*-----------Panel for color/brand period display of selected item (label above)----------- */

        JPanel buttonPanen1Cont5 = new JPanel();
        buttonPanen1Cont5.setLayout(new GridLayout(1, 2, 5, 5));

        buttonPanen1Cont5.add(Product_5);
        buttonPanen1Cont5.add(panelCont5);

        /*-----------Panel for available items display of selected item----------- */

        JPanel buttonPanen1Cont6 = new JPanel();
        buttonPanen1Cont6.setLayout(new GridLayout(1, 2, 5, 5));

        JLabel Product_Quantity = new JLabel("Items Available");
        buttonPanen1Cont6.add(Product_Quantity);
        buttonPanen1Cont6.add(panelCont6);

        /*-----------Adding panels to buttons panel1----------- */

        buttonsPanel1.add(buttonPanen1Cont);
        buttonsPanel1.add(buttonPanen1Cont2);
        buttonsPanel1.add(buttonPanen1Cont3);
        buttonsPanel1.add(buttonPanen1Cont4);
        buttonsPanel1.add(buttonPanen1Cont5);
        buttonsPanel1.add(buttonPanen1Cont6);

        JPanel buttonsPanel2 = new JPanel();
        buttonsPanel.add(buttonsPanel1);
        buttonsPanel.add(buttonsPanel2);

        //add to cart button size
        AddProduct.setPreferredSize(new Dimension(200, 35));


        //----------------action listener for add to cart button--------------------------------
        AddProduct.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow != -1) {
                    Object[] selectedRowData = data[selectedRow];
                    for (Product product : productList) {
                        if (product.getProductId().equals(String.valueOf(selectedRowData[0]))) {
                            // Check if the product is already in the cart
                            boolean productInCart = false;
                            for (Product cartProduct : user.ShoppingCart.getProducts()) {
                                if (cartProduct.getProductId().equals(product.getProductId())) {
                                    // Product is already in the cart, update the quantity
                                    int newQuantity = cartProduct.getQuantity() + 1;
                                    int maxQuantity = cartProduct.getAvailableItems();
                                    // Check if adding 1 exceeds the maximum quantity limit
                                    if (newQuantity <= maxQuantity) {
                                        cartProduct.setQuantity(newQuantity);
                                        JOptionPane.showMessageDialog(HomeGui.this, "Product Added Successfully");

                                    } else {
                                        // show a message to the user that adding one exceeds the limit
                                        JOptionPane.showMessageDialog(HomeGui.this, "Quantity Exceeds the Remaining Quantity");
                                    }
                                    productInCart = true;
                                    break;
                                }
                            }

                            // If the product is not in the cart, add it with quantity 1
                            if (!productInCart) {
                                Product cartProduct;
                                if (product instanceof Electronics) {
                                    Electronics electronicsProduct = (Electronics) product;
                                    cartProduct = new Electronics(
                                            electronicsProduct.getProductId(),
                                            electronicsProduct.getProductName(),
                                            electronicsProduct.getAvailableItems(),
                                            electronicsProduct.getPrice(),
                                            electronicsProduct.getWarrantyPeriod(),
                                            electronicsProduct.getBrand()
                                            );
                                } else if (product instanceof Clothing) {
                                    Clothing clothingProduct = (Clothing) product;
                                    cartProduct = new Clothing(
                                            clothingProduct.getProductId(),
                                            clothingProduct.getProductName(),
                                            clothingProduct.getAvailableItems(),
                                            clothingProduct.getPrice(),
                                            clothingProduct.getSize(),
                                            clothingProduct.getColor()
                                            );
                                } else {
                                    // Handle other types of products if needed
                                    cartProduct = new Clothing(
                                            product.getProductId(),
                                            product.getProductName(),
                                            product.getAvailableItems(),
                                            product.getPrice(),
                                            "a",
                                            "s");
                                }
                                cartProduct.setQuantity(1);
                                user.ShoppingCart.addProduct(cartProduct);
                                JOptionPane.showMessageDialog(HomeGui.this, "Product Added Successfully");


                            }
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(HomeGui.this, "Please select a product to add to the cart.");
                }

            }
        });

        buttonsPanel2.add(AddProduct);

        /*----------------  Main Panel ---------------- */


        //main panel creation
        JPanel mainPanel = new JPanel();
        //setting layout for main panel
        mainPanel.setLayout(new BorderLayout());
        //setting color
        mainPanel.setBackground(new Color(128, 128, 128));

        //adding the above panels to the main panel
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        //adds man panel to frame
        add(mainPanel);

        //creating the title of frame
        setTitle("WestMinster Shopping Center");
        //size of frame
        setSize(900, 800);
        setMinimumSize(new Dimension(300, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        }


}


public class User {
    private String username;
    private String password;
    private Boolean Fresh_Customer = true;

    public ShoppingCart ShoppingCart;

    //constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.ShoppingCart = new ShoppingCart();
    }


    //getters and setters
    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getFreshCustomer() {
        return Fresh_Customer;
    }

    public void setFreshCustomer(Boolean Fresh_Customer) {
        this.Fresh_Customer = Fresh_Customer;
    }

}
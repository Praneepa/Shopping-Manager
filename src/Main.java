import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Westminster Online Shopping System! \n Please Select an Option");
            System.out.println("1- Open GUI");
            System.out.println("2- Open Manager Console");

            //new shopping manager instance created
            WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
            User user=new User("1","1");
            int option=0;
            try{
                option= input.nextInt();
            }
            catch (Exception E){
                input.nextLine();
            }

            if (option == 1) {
                System.out.println("Enter User Name: ");
                String enteredUserName = input.next();

                System.out.println("Enter Password: ");
                String enteredPassword = input.next();

                //if password/username matches given
                if(enteredPassword.equals(user.getPassword()) && enteredUserName.equals(user.getUsername())){
                    System.out.println("Opening GUI ...");
                    HomeGui home = new HomeGui();
                    home.HomeGui(shoppingManager.getInventory(),user);
                }
                else{    //if wrong password/username
                    System.out.println("Please enter valid Password & User Name.");

                }

            } else if (option == 2) {
                validateManager(); //validate manager password

                shoppingManager.displayMenu();    //display menu console
                break;

            } else {
                System.out.println("Please enter valid input.");
            }
        }

    }



//validate manager password
    private static void validateManager() {
        Scanner input = new Scanner(System.in);
        while (true){
            final String mPassword = "m" ;
            System.out.println("Enter Password: ");

            String enteredPassword = input.next();

            if (enteredPassword.equals(mPassword)){
                break;
            }
            else {
                System.out.println("Wrong Password. Try Again");

            }

        }
    }
}

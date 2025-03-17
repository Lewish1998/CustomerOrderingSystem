package com.ordermanager;

import java.sql.Connection;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.ordermanager.models.Order;
import com.ordermanager.daos.CustomerDAOImpl;
import com.ordermanager.daos.ItemDAO;
import com.ordermanager.daos.ItemDAOImpl;
import com.ordermanager.daos.OrderDAOImpl;
import com.ordermanager.daos.OrderItemDAOImpl;
import com.ordermanager.models.ConnectionManager;

public class Main {

    // Create a scanner to accept user input
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create a boolean which will dictate whether or not the while loop is running
        boolean running = true;

        // Call the startup screen before creating the while loop so that it only displays when first starting the program
        startupScreen();

        while (running) {
            // Call function to clear screen. Improves legibility of the code.
            clearScreen();
            // Print out the opttioins for the main menu
            System.out.println("""
                ========================================
                 CUSTOMER ORDERING SYSTEM - MAIN MENU
                ========================================
                  1. Manage Orders
                  2. Manage Customers
                  3. Manage Items
                  4. Exit
                """);
            // Create a switch statement for the user input. The specified method is called dependent on the users input.
            // Changed to switch statement instead of for loop as cleaner and easier to read for this use case.
            switch (getChoice()) {
                case "1" -> ordersMenu();
                case "2" -> customersMenu();
                case "3" -> itemsMenu();
                case "4" -> exitProgram();
                default -> invalidChoice();
            }
        }
    }

    // sttartup function called before the while loop
    private static void startupScreen() {
        clearScreen();
        System.out.println("Customer Ordering System starting...");
        sleep(1);
        clearScreen();
        System.out.println("Welcome to the Customer Ordering System");
        sleep(1);
        clearScreen();
    }


    // ########## Orders ##########

    /* This method prints a list of options for the user. A switch statement is then used to run the desired method based on the users input. 
     * If an invalid choice is made by the user then it will run the invalid choioce method.
    */
    private static void ordersMenu() {
        while (true) {
            clearScreen();
            System.out.println("""
                ========================================
                MANAGE ORDERS
                ========================================
                  1. Create Order
                  2. View Orders
                  3. Edit Order
                  4. Delete Order
                  5. Back to Main Menu
                """);
            switch (getChoice()) {
                case "1" -> createOrder();
                case "2" -> viewOrders();
                case "3" -> editOrder();
                case "4" -> deleteOrder();
                case "5" -> { return; }
                default -> invalidChoice();
            }
        }
    }

    // This method takes in the user input of customer id, and returns information on the specified user.
    // It makes user of the connection manager class to create a database connection.
    // It also uses abstracttion as it is using methods from the OrderDAOImpl class to perform the necessary SQL commands required to return the desired data.
    private static void createOrder() {
        clearScreen();
        System.out.println("Enter customer ID:");
        int customerId = getIntChoice();
        
        Optional<Connection> conn = ConnectionManager.connection();
        if (conn.isPresent()) {
            OrderDAOImpl orderDAO = new OrderDAOImpl(conn.get());
            orderDAO.createOrder(customerId);
            System.out.println("Order Created Successfully!");
        } else {
            System.out.println("Database Connection Failed.");
        }
        promptEnterToContinue();
    }

    // private static void viewOrders() {
    //     clearScreen();
    //     System.out.println("Viewing Orders...\n");

    //     Optional<Connection> conn = ConnectionManager.connection();
    //     if (conn.isPresent()) {
    //         OrderDAOImpl orderDAO = new OrderDAOImpl(conn.get());
    //         orderDAO.getOrdersByCustomerId(1).forEach(System.out::println);
    //     } else {
    //         System.out.println("Database Connection Failed.");
    //     }
    //     promptEnterToContinue();
    // }

    static void viewOrders() {
        clearScreen();
        System.out.println("Viewing Orders...\n");

        Optional<Connection> conn = ConnectionManager.connection();
        if (conn.isPresent()) {
            OrderDAOImpl orderDAO = new OrderDAOImpl(conn.get());
            orderDAO.getOrders();
        } else {
            System.out.println("Database Connection Failed.");
        }
        promptEnterToContinue();
    }

    private static void editOrder() {
        clearScreen();
        System.out.println("Enter Order ID to edit:");
        int orderId = getIntChoice();
        System.out.println("Enter Item ID to edit:");
        int itemId = getIntChoice();
        System.out.println("Enter new Item ID:");
        int newItemId = getIntChoice();
        System.out.println("Enter new Item Quantity:");
        int itemQuantity = getIntChoice();

        Optional<Connection> conn = ConnectionManager.connection();
        if (conn.isPresent()) {
            OrderItemDAOImpl orderItemDAO = new OrderItemDAOImpl(conn.get());
            orderItemDAO.addItemToOrder(orderId, 1, 1, 1);
            System.out.println("Item added to order.");
        } else {
            System.out.println("Database Connection Failed.");
        }

        promptEnterToContinue();
    }

    private static void deleteOrder() {
        clearScreen();
        System.out.println("Enter Order ID to delete:");
        int orderId = getIntChoice();
        // Implement delete logic here
        Optional<Connection> conn = ConnectionManager.connection();
        if (conn.isPresent()) {
            OrderDAOImpl orderDAO = new OrderDAOImpl(conn.get());
            boolean res = orderDAO.deleteOrder(orderId);
            if (res) {
            System.out.println("Order deleted successfully.");
            } else {
            System.out.println("Order not found.");
            }
        } else {
            System.out.println("Database Connection Failed.");
        }
        promptEnterToContinue();
    }


    // ########## Customers ##########

    private static void customersMenu() {
        while (true) {
            clearScreen();
            System.out.println("""
                ========================================
                MANAGE CUSTOMERS
                ========================================
                  1. Add Customer
                  2. View All Customers
                  3. View Customer By ID
                  4. Delete Customer
                  5. Back to Main Menu
                """);
            switch (getChoice()) {
                case "1" -> createCustomer();
                case "2" -> viewCustomers();
                case "3" -> viewCustomerByID();
                case "4" -> deleteCustomer();
                case "5" -> { return; }
                default -> invalidChoice();
            }
        }
    }

    private static void createCustomer() {
        clearScreen();
        System.out.println("Enter first name");
        String fname = scanner.nextLine();
        System.out.println("Enter last name");
        String lname = scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();
        
        Optional<Connection> conn = ConnectionManager.connection();
        if (conn.isPresent()) {
            CustomerDAOImpl customerDAO = new CustomerDAOImpl(conn.get());
            customerDAO.createCustomer(1, fname, lname, email);
            System.out.println("Customer Created Successfully!");
        } else {
            System.out.println("Database Connection Failed.");
        }
        promptEnterToContinue();
    }

    private static void viewCustomers() {
        clearScreen();
        System.out.println("Viewing Customers...\n");

        Optional<Connection> conn = ConnectionManager.connection();
        if (conn.isPresent()) {
            CustomerDAOImpl customerDAO = new CustomerDAOImpl(conn.get());
            customerDAO.getCustomers();
        } else {
            System.out.println("Database Connection Failed.");
        }

        promptEnterToContinue();
    }

    public static void viewCustomerByID() {
        clearScreen();
        System.out.println("Enter Custommer ID to view:");
        int customerID = getIntChoice();
        // Implement view logic here
        Optional<Connection> conn = ConnectionManager.connection();
        if (conn.isPresent()) {
            CustomerDAOImpl customerDAO = new CustomerDAOImpl(conn.get());
            customerDAO.getCustomerById(customerID);
        } else {
            System.out.println("Database Connection Failed.");
        }
        promptEnterToContinue();
    }

    private static void deleteCustomer() {
        clearScreen();
        System.out.println("Enter Custommer ID to delete:");
        int customerID = getIntChoice();
        // Implement delete logic here
        Optional<Connection> conn = ConnectionManager.connection();
        if (conn.isPresent()) {
            CustomerDAOImpl customerDAO = new CustomerDAOImpl(conn.get());
            boolean res = customerDAO.deleteCustomer(customerID);
            if (res) {
            System.out.println("Customer deleted successfully.");
            } else {
            System.out.println("Customer not found.");
            }
        } else {
            System.out.println("Database Connection Failed.");
        }
        promptEnterToContinue();
    }

    public static void editCustomer() {
        clearScreen();
        System.out.println("Enter Customer ID to edit:");
        int customerID = getIntChoice();
        System.out.println("Enter new first name:");
        String fname = scanner.nextLine();
        System.out.println("Enter new last name:");
        String lname = scanner.nextLine();
        System.out.println("Enter new email:");
        String email = scanner.nextLine();

        Optional<Connection> conn = ConnectionManager.connection();
        if (conn.isPresent()) {
            CustomerDAOImpl customerDAO = new CustomerDAOImpl(conn.get());
            boolean res = customerDAO.updateCustomer(customerID, fname, lname, email);
            if (res) {
            System.out.println("Customer updated successfully.");
            } else {
            System.out.println("Customer not found.");
            }
        } else {
            System.out.println("Database Connection Failed.");
        }
        promptEnterToContinue();
    }


    // ########## Items ##########

    private static void itemsMenu() {
        while (true) {
            clearScreen();
            System.out.println("""
                ========================================
                MANAGE ITEMS
                ========================================
                  1. Add Item
                  2. View Items
                  3. Delete Item
                  4. Back to Main Menu
                """);
            switch (getChoice()) {
                case "1" -> createItem();
                case "2" -> viewItems();
                case "3" -> System.out.println("Deleting item... (Not Implemented)");
                case "4" -> { return; }
                default -> invalidChoice();
            }
        }
    }

    public static void viewItems() {
        clearScreen();
        System.out.println("Viewing Items...\n");

        Optional<Connection> conn = ConnectionManager.connection();
        if (conn.isPresent()) {
            ItemDAO itemDAO = new ItemDAOImpl(conn.get());
            itemDAO.getItems();
        } else {
            System.out.println("Database Connection Failed.");
        }
        promptEnterToContinue();
    }

    public static void createItem() {
        clearScreen();
        System.out.println("Enter item name");
        String name = scanner.nextLine();
        System.out.println("Enter item price");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter item quantity");
        int quantity = scanner.nextInt();
        
        Optional<Connection> conn = ConnectionManager.connection();
        if (conn.isPresent()) {
            ItemDAOImpl itemDAO = new ItemDAOImpl(conn.get());
            itemDAO.createItem(name, price, quantity);
            System.out.println("Item Created Successfully!");
        } else {
            System.out.println("Database Connection Failed.");
        }
        promptEnterToContinue();
    }

    public void deleteItem() {
        clearScreen();
        System.out.println("Enter Item ID to delete:");
        int itemID = getIntChoice();
        // Implement delete logic here
        Optional<Connection> conn = ConnectionManager.connection();
        if (conn.isPresent()) {
            ItemDAOImpl itemDAO = new ItemDAOImpl(conn.get());
            boolean res = itemDAO.deleteItem(itemID);
            if (res) {
            System.out.println("Item deleted successfully.");
            } else {
            System.out.println("Item not found.");
            }
        } else {
            System.out.println("Database Connection Failed.");
        }
        promptEnterToContinue();
    }

    // ########## Utility Methods ##########

    // These methods were created to make sure that the code in the main method is cleaner and allows for more reusability.
    // The exitProgram metthod clears the screen, says goodbye to the user, and then exits the program.
    private static void exitProgram() {
        clearScreen();
        System.out.println("Goodbye!");
        sleep(1);
        System.exit(0);
    }

    // The get choice method prints an indication for where the user will put their input, and then returns the trimmed user input.
    private static String getChoice() {
        System.out.print(">>> ");
        return scanner.nextLine().trim();
    }

    // The getIntChoice method is very similar to the above method, but ensures that the user input is recieved as an integer instead of a String
    private static int getIntChoice() {
        while (true) {
            try {
                System.out.print(">>> ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please try again.");
            }
        }
    }

    private static void invalidChoice() {
        System.out.println("Invalid choice. Please try again.");
        sleep(1);
    }

    // When called waits for the user to press a key before continuing with the program.
    private static void promptEnterToContinue() {
        System.out.println("\nPress [ENTER] to continue...");
        scanner.nextLine();
    }

    // Clears the screen by printing a specific command
    // See https://stackoverflow.com/questions/55672661/what-does-printf-033h-033j-do-in-c for explination of print method (In C)
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}

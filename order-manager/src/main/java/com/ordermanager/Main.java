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

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        startupScreen();

        while (running) {
            clearScreen();
            System.out.println("""
                ========================================
                 CUSTOMER ORDERING SYSTEM - MAIN MENU
                ========================================
                  1. Manage Orders
                  2. Manage Customers
                  3. Manage Items
                  4. Exit
                """);
            switch (getChoice()) {
                case "1" -> ordersMenu();
                case "2" -> customersMenu();
                case "3" -> itemsMenu();
                case "4" -> exitProgram();
                default -> invalidChoice();
            }
        }
    }

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

    // private static void editCustomer() {
    //     clearScreen();
    //     System.out.println("Enter Order ID to edit:");
    //     int orderId = getIntChoice();

    //     Optional<Connection> conn = ConnectionManager.connection();
    //     if (conn.isPresent()) {
    //         OrderItemDAOImpl orderItemDAO = new OrderItemDAOImpl(conn.get());
    //         orderItemDAO.addItemToOrder(orderId, 1, 1, 1);
    //         System.out.println("Item added to order.");
    //     } else {
    //         System.out.println("Database Connection Failed.");
    //     }

    //     promptEnterToContinue();
    // }

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
                case "1" -> System.out.println("Adding item... (Not Implemented)");
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

    private static void exitProgram() {
        clearScreen();
        System.out.println("Goodbye!");
        sleep(1);
        System.exit(0);
    }

    private static String getChoice() {
        System.out.print(">>> ");
        return scanner.nextLine().trim();
    }

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

    private static void promptEnterToContinue() {
        System.out.println("\nPress [ENTER] to continue...");
        scanner.nextLine();
    }

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

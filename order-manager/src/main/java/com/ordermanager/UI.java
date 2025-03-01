package com.ordermanager;

import java.sql.Connection;
import java.util.Optional;
import java.util.Scanner;
import com.ordermanager.models.Order;

import com.ordermanager.daos.OrderDAOImpl;
import com.ordermanager.daos.OrderItemDAOImpl;
import com.ordermanager.models.ConnectionManager;

public class UI {

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("Customer Ordering System starting...");

        // Main menu
        while (running) {
            System.out.println("""
                    1. Orders
                    2. Customers
                    3. Items
                    4. Exit
                    """);
            String input = choice();

            firstCase(input);
        }
    }

    public static String choice() {
        Scanner s = new Scanner(System.in);
        System.out.print(">>> ");
        String userInput = s.nextLine();
        return userInput;
    }

    public static void firstCase(String input) {
        switch (input) {
            case "1":
                System.out.println("Loading orders...");
                orders();
                break;
            case "2":
                System.out.println("Loading customers...");
                customers();
                break;
            case "3":
                System.out.println("Loading items...");
                items();
                break;
            case "4":
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void orders() {
        
        System.out.println("""
                1. Create order
                2. View orders
                3. Delete order
                4. Back / exit
                """);
        String input = choice();
        switch (input) {
            case "1":
                System.out.println("Creating order...");
                Optional<Connection> conn = ConnectionManager.connection();
                if (conn.isPresent()) {
                    OrderDAOImpl orderDAO = new OrderDAOImpl(conn.get());
                    OrderItemDAOImpl orderItemDAO = new OrderItemDAOImpl(conn.get());
                    // Add order number input
                    System.out.println("Enter customer number");
                    String customerNumber = choice();
                    orderDAO.createOrder(Integer.valueOf(customerNumber));
                } 
                // Add logic to create order
                break;
            case "2":
                System.out.println("Viewing orders...");
                Optional<Connection> conn2 = ConnectionManager.connection();
                if (conn2.isPresent()) {
                    OrderDAOImpl orderDAO = new OrderDAOImpl(conn2.get());
                    orderDAO.getOrdersByCustomerId(1).forEach(System.out::println);
                    System.out.println();
                } 
                // Add logic to view orders
                break;
            case "3":
                System.out.println("Deleting order...");
                // Add logic to delete order
                break;
            case "4":
                System.out.println("Exiting orders menu...");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void customers() {
        System.out.println("""
                1. Add customer
                2. View customers
                3. Delete customer
                4. Back / exit
                """);
        String input = choice();
        switch (input) {
            case "1":
                System.out.println("Adding customer...");
                // Add logic to add customer
                break;
            case "2":
                System.out.println("Viewing customers...");
                // Add logic to view customers
                break;
            case "3":
                System.out.println("Deleting customer...");
                // Add logic to delete customer
                break;
            case "4":
                System.out.println("Exiting customers menu...");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void items() {
        System.out.println("""
                1. Add item
                2. View items
                3. Delete item
                4. Back / exit
                """);
        String input = choice();
        switch (input) {
            case "1":
                System.out.println("Adding item...");
                // Add logic to add item
                break;
            case "2":
                System.out.println("Viewing items...");
                // Add logic to view items
                break;
            case "3":
                System.out.println("Deleting item...");
                // Add logic to delete item
                break;
            case "4":
                System.out.println("Exiting items menu...");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}
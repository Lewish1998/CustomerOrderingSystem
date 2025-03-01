package com.ordermanager;

import com.ordermanager.daos.OrderDAOImpl;
import com.ordermanager.daos.OrderItemDAOImpl;
import com.ordermanager.models.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/customer_ordering_system", "postgres", "postgres");

            OrderDAOImpl orderDAO = new OrderDAOImpl(conn);
            OrderItemDAOImpl orderItemDAO = new OrderItemDAOImpl(conn);

            // create order
            int orderId = orderDAO.createOrder(1); // For customer ID 1
            System.out.println("Created Order ID: " + orderId);

            // add items to order
            orderItemDAO.addItemToOrder(orderId, 1, 2, 0.75); // Apple x2
            orderItemDAO.addItemToOrder(orderId, 2, 1, 0.99); // Orange x1

            // print all orders
            List<Order> orders = orderDAO.getOrdersByCustomerId(1);
            for (Order order : orders) {
                System.out.println(order);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

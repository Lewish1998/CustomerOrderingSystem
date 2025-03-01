package com.ordermanager.daos;

import java.util.List;

import com.ordermanager.models.Order;

public interface OrderDAO {
    Order getOrderById(int id);
    List<Order> getOrdersByCustomerId(int customerId);
    int createOrder(int customerId);
    boolean deleteOrder(int id);
}

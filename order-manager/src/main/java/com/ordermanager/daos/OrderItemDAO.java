package com.ordermanager.daos;

import java.util.List;

import com.ordermanager.models.OrderItem;

public interface OrderItemDAO {
    List<OrderItem> getItemsByOrderId(int order_id);
    boolean addItemToOrder(int orderId, int itemId, int quantity, double price);
    boolean deleteOrderItem(int id);
} 
package com.ordermanager.daos;

import com.ordermanager.models.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {
    private final Connection conn;

    public OrderItemDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<OrderItem> getItemsByOrderId(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderItems.add(new OrderItem(
                    rs.getInt("id"),
                    rs.getInt("order_id"),
                    rs.getInt("item_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("item_price_at_order")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    @Override
    public boolean addItemToOrder(int orderId, int itemId, int quantity, double price) {
        String sql = "INSERT INTO order_items (order_id, item_id, quantity, item_price_at_order) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, itemId);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteOrderItem(int id) {
        String sql = "DELETE FROM order_items WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

package com.ordermanager.daos;

import com.ordermanager.models.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private final Connection conn;

    public OrderDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Order getOrderById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Order(rs.getInt("id"), rs.getInt("customer_id"), rs.getTimestamp("created_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getOrders() {
        String sql = "SELECT * FROM orders;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Customer ID: " + rs.getInt("customer_id") + ", Created At: " + rs.getTimestamp("created_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int createOrder(int customerId) {
        String sql = "INSERT INTO orders (customer_id) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, customerId);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    System.out.println("Order created with ID: " + keys.getInt(1) + "\n");
                    return keys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean deleteOrder(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

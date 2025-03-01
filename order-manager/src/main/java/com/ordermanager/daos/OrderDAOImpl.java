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
    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"), rs.getInt("customer_id"), rs.getTimestamp("created_at")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
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

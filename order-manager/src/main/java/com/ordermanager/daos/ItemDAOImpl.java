package com.ordermanager.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;

import com.ordermanager.models.Item;

public class ItemDAOImpl implements ItemDAO {
    private final Connection conn;

    public ItemDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Item getItemById(int id) {
        return null;
    }

    @Override
    public int createItem(String name, double price) {
        return 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return false;
    }

    @Override
    public List<Item> getItems() {
        String sql = "SELECT * FROM items;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Price: " + rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}

package com.ordermanager.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

public class ConnectionManager {
    public static Optional<Connection> connection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/customer_ordering_system", "postgres", "postgres");
            return Optional.of(conn);
        } catch (Exception e) {
            System.err.println(e);
            return Optional.empty();
        }
    }
}

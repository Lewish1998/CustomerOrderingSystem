package com.ordermanager.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

// Create a reusable connection manager class to be used throughout the project. This will improve readability of the code and allow for the removal of any redundant code.
public class ConnectionManager {
    public static Optional<Connection> connection() {
        try {
            // Connect to a local PostgreSQL database passing username and password.
            // TODO: Move connection parameters to environment variables.
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/customer_ordering_system", "postgres", "postgres");
            return Optional.of(conn);
        } catch (Exception e) {
            System.err.println(e);
            return Optional.empty();
        }
    }
}

package com.ordermanager.models;

import java.sql.Timestamp;

public record Customer(int id, String fname, String lname, String email, Timestamp created_at, Timestamp last_updated) {}
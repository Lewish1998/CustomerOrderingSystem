package com.ordermanager.models;

import java.sql.Timestamp;

public record Item (int id, String name, double price, int quantity, Timestamp created_at, Timestamp last_updated) {}

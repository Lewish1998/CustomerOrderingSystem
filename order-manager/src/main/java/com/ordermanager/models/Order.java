package com.ordermanager.models;

import java.sql.Timestamp;

public record Order(int id, int customer_id, Timestamp created_at) {}

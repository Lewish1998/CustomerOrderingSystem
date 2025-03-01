package com.ordermanager.models;

public record OrderItem(int id, int order_id, int item_id, int quantity, double item_price_at_order) {}

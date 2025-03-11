package com.ordermanager.daos;

import java.util.List;

import com.ordermanager.models.Item;

public interface ItemDAO {
    Item getItemById(int id);
    int createItem(String name, double price, int quantity);
    boolean deleteItem(int id);
    List<Item> getItems();
}

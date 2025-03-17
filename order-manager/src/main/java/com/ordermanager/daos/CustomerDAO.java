package com.ordermanager.daos;

import java.util.List;

import com.ordermanager.models.Customer;

public interface CustomerDAO {
    Customer getCustomerById(int id);
    List<Customer> getCustomers();
    int createCustomer(int customerId, String fname, String lname, String email);
    boolean deleteCustomer(int id);
    boolean updateCustomer(int id, String fname, String lname, String email);
}

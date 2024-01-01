package com.spring.jdbclientdemo.service;

import com.spring.jdbclientdemo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<Customer> getAllCustomer();

    Optional<Customer> getCustomerById(int id);

    void insert(Customer customer);

    void update(int id, Customer customer);

    void delete(int id);
}

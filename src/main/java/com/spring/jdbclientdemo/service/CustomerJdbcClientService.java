package com.spring.jdbclientdemo.service;

import com.spring.jdbclientdemo.model.Customer;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerJdbcClientService implements ICustomerService{

    private final JdbcClient jdbcClient;

    public CustomerJdbcClientService(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Customer> getAllCustomer(){
        return jdbcClient.sql("select id, name, lastname, birthday from customer").query(Customer.class).list();
    }

    public Optional<Customer> getCustomerById(int id){
        return jdbcClient.sql("select id, name, lastname, birthday from customer where id= :id").param("id", id).query(Customer.class).optional();
    }

    public void insert(Customer customer){
        int inserted = jdbcClient.sql("insert into customer (id, name, lastname, birthday) values (?,?,?,?)")
                .params(List.of(customer.id(), customer.name(), customer.lastname(), customer.birthday()))
                .update();
        Assert.state(inserted == 1 , "An exception error occurred while inserting customer");
    }

    public void update(int id, Customer customer){
        int updated = jdbcClient.sql("update customer set name = ?, lastname = ?, birthday = ? where id = ?")
                .params(List.of(customer.name(), customer.lastname(), customer.birthday(), id))
                .update();
        Assert.state(updated == 1, "An exception error occurred while updating customer");
    }

    @Override
    public void delete(int id) {
        int deleted = jdbcClient.sql("delete from customer where id = :id").param("id",id).update();
        Assert.state(deleted == 1, "An exception error occurred while updating customer");
    }
}

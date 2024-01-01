package com.spring.jdbclientdemo.service;

import com.spring.jdbclientdemo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerJdbcTemplateService implements ICustomerService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final JdbcTemplate jdbcTemplate;

    public CustomerJdbcTemplateService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Customer> rowMapper = (rs, row)-> new Customer(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("lastname"),
            rs.getDate("birthday")
    );

    @Override
    public List<Customer> getAllCustomer() {
        return jdbcTemplate.query("select id, name, lastname, birthday from customer", rowMapper);
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {
        Customer customer = null;
        try{
            customer = jdbcTemplate.queryForObject("select id, name, lastname, birthday from customer where id = ?", rowMapper,  id );
        } catch (DataAccessException ex){
          LOG.error("Data not found. Id parameter: " + id, ex);
        }
        return Optional.ofNullable(customer);
    }

    @Override
    public void insert(Customer customer) {
        int inserted = jdbcTemplate.update("insert into customer (id, name, lastname, birthday) values (?,?,?,?)",
                customer.id(), customer.name(), customer.lastname(),customer.birthday());
        Assert.state(inserted == 1 , "An exception error occurred while inserting customer");
    }

    @Override
    public void update(int id, Customer customer) {
        int updated = jdbcTemplate.update("update customer set name = ?, lastname = ?, birthday = ? where id = ? ",
                customer.name(), customer.lastname(),customer.birthday(), id);
        Assert.state(updated == 1 , "An exception error occurred while updating customer");
    }

    @Override
    public void delete(int id) {
        int deleted = jdbcTemplate.update("delete from customer where id = ?", id);
        Assert.state(deleted == 1 , "An exception error occurred while deleting customer");
    }


}

package com.spring.jdbclientdemo;

import com.spring.jdbclientdemo.model.Customer;
import com.spring.jdbclientdemo.service.CustomerJdbcClientService;
import com.spring.jdbclientdemo.service.CustomerJdbcTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Optional;

@SpringBootApplication
public class Application implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private CustomerJdbcClientService customerJdbcClientService;

	@Autowired
	private CustomerJdbcTemplateService customerJdbcTemplateService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// jdbClientRun();

		// jdbcTemplateRun();
	}

	private void jdbcTemplateRun(){
		// insert data
		Customer customer1 = new Customer(1, "Alice", "Lop", new Date());
		customerJdbcTemplateService.insert(customer1);

		Customer customer2 = new Customer(2, "Julia", "Robert", new Date());
		customerJdbcTemplateService.insert(customer2);

		// print data
		customerJdbcTemplateService.getAllCustomer().forEach(System.out::println);

		// get by id data
		Optional<Customer> customer = customerJdbcTemplateService.getCustomerById(1);
		System.out.println("Get by id: " +  customer.get());

		// update by id data
		Customer newCustomer = new Customer(1, "Jack","Jacert", new Date());
		customerJdbcTemplateService.update(1, newCustomer);
		Optional<Customer> newDBCustomer = customerJdbcTemplateService.getCustomerById(1);
		System.out.println("update data: " + newDBCustomer.get());

		// delete by id
		customerJdbcTemplateService.delete(1);
		customerJdbcTemplateService.getAllCustomer().forEach(System.out::println);


	}

	private void jdbClientRun(){

		// insert data
		Customer customer1 = new Customer(1, "Alice", "Lop", new Date());
		customerJdbcClientService.insert(customer1);

		Customer customer2 = new Customer(2, "Julia", "Robert", new Date());
		customerJdbcClientService.insert(customer2);

		// print data
		customerJdbcClientService.getAllCustomer().forEach(System.out::println);

		// get by id data
		Optional<Customer> customer = customerJdbcClientService.getCustomerById(1);
		System.out.println("Get by id: " + customer.get());

		// update by id data
		Customer newCustomer = new Customer(1, "Jack","Jakert", new Date());
		customerJdbcClientService.update(1, newCustomer);
		Optional<Customer> newDBCustomer = customerJdbcClientService.getCustomerById(1);
		System.out.println("update data: " + newDBCustomer.get());


		// delete by id
		customerJdbcClientService.delete(1);
		customerJdbcClientService.getAllCustomer().forEach(System.out::println);

	}
}

package com.spring.jdbclientdemo.model;

import java.util.Date;

public record Customer(int id, String name, String lastname, Date birthday) {
}

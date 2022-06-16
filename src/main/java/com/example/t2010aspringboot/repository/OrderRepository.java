package com.example.t2010aspringboot.repository;

import com.example.t2010aspringboot.entity.Order;
import com.example.t2010aspringboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, String> {
}

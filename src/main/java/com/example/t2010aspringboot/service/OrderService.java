package com.example.t2010aspringboot.service;

import com.example.t2010aspringboot.entity.Order;
import com.example.t2010aspringboot.entity.Product;
import com.example.t2010aspringboot.repository.OrderRepository;
import com.example.t2010aspringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> findAll (){
        return orderRepository.findAll();
    }

    public Optional<Order> findById (String id){
        return orderRepository.findById(id);
    }

    public Order save (Order order){
        return orderRepository.save(order);
    }

    public void delete (String id){
        orderRepository.deleteById(id);
    }
}

package com.example.t2010aspringboot.controller;

import com.example.t2010aspringboot.entity.Order;
import com.example.t2010aspringboot.entity.Product;
import com.example.t2010aspringboot.service.OrderService;
import com.example.t2010aspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getList() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable String id) {
        Optional<Order> optionalOrder = orderService.findById(id);
        if (!optionalOrder.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalOrder.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.save(order));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Order> update(@PathVariable String id, @RequestBody Order order) {
        Optional<Order> optionalOrder = orderService.findById(id);
        if (!optionalOrder.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Order existOrder = optionalOrder.get();
        existOrder.setUserId(order.getUserId());
        existOrder.setTotalPrice(order.getTotalPrice());
        return ResponseEntity.ok(orderService.save(existOrder));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!orderService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        orderService.delete(id);
        System.out.println("Success");
        return ResponseEntity.ok().build();
    }

}

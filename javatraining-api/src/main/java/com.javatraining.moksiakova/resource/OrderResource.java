package com.javatraining.moksiakova.resource;

import com.javatraining.moksiakova.dto.CustomerDTO;
import com.javatraining.moksiakova.dto.OrderDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping(value = "/orders")
public interface OrderResource {

    @GetMapping("/{id}")
    OrderDTO findOrder(@PathVariable Integer id);

    @GetMapping
    Collection<OrderDTO> findAllOrder();

    @PostMapping
    OrderDTO createOrder(@RequestBody OrderDTO order);

    @PutMapping
    OrderDTO updateOrder(@RequestBody OrderDTO order);

    @DeleteMapping("/{id}")
    void deleteOrder(@PathVariable Integer id);
}

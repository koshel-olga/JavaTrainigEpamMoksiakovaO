package com.javatraining.moksiakova.repositories.stub;

import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("local")
public class OrderRepositoryStub implements OrderRepository {
    @Override
    public Order findOrDie(int orderId) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public void delete(Order order) {

    }
}

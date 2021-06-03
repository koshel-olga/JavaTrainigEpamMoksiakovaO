package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

/**
 * class for work with table customer in Database.
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerRepositoryImpl implements CustomerRepository {

    private final EntityManager entityManager;

    @Override
    public Customer findOrDie(int customerId) throws EntityNotFoundException {
        Customer customer = entityManager.find(Customer.class, customerId);
        if (Objects.isNull(customer)) {
            throw new EntityNotFoundException(
                    String.format("Can't find Customer with ID %d", customerId));
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = entityManager.createQuery("Select a From Customer a", Customer.class).getResultList();
        return customers;
    }

    @Override
    public void save(Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.remove(customer);
        entityManager.getTransaction().commit();
    }
}

package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Customer;

import javax.persistence.EntityNotFoundException;
import java.util.List;


public interface CustomerRepository {
    /**
     * Find {@link Customer} in database by id.
     * @param customerId identification of customer.
     * @return {@link Customer}.
     * @throws EntityNotFoundException
     */
    Customer findOrDie(int customerId) throws EntityNotFoundException;

    /**
     * Get collection {@link Customer} in database.
     * @return list of customers.
     */
    List<Customer> findAll();

    /**
     * Save {@link Customer} in database.
     * @param customer
     */
    void save(Customer customer);

    /**
     * Delete {@link Customer} from database by customer_id.
     * @param customer
     */
    void delete(Customer customer);
}

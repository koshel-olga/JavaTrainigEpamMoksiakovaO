package com.javatraining.moksiakova.components;

import com.javatraining.moksiakova.domain.entity.Customer;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface CustomerComponent {
    /**
     *
     * @param customerName
     * @param customerPhone
     * @return
     */
    Customer createCustomer(String customerName, String customerPhone);

    /**
     * Update {@link Customer} by customer_id.
     * @param customerId
     * @param customerPhone
     * @param customerName
     */
    Customer updateCustomer(int customerId, String customerName, String customerPhone);

    /**
     * Find {@link Customer} by customer_id.
     * @param customerId
     * @return
     */
    Customer findById(int customerId) throws EntityNotFoundException;

    /**
     * Find all {@link Customer}.
     * @return
     */
    List<Customer> findAll();

    /**
     * Delete {@link Customer} by customer_id.
     * @param customerId
     */
    void deleteCustomer(int customerId);
}

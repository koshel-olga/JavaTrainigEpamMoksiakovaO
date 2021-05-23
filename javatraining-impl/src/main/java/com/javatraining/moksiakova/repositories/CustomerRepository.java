package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Customer;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.util.Objects;

/**
 * class for work with table customer in Database.
 */
@RequiredArgsConstructor
public class CustomerRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("javatraining-unit");
    private EntityManager em = emf.createEntityManager();

    /**
     * Find {@link Customer} in database by id.
     * @param customerId identification of customer.
     * @return {@link Customer}.
     * @throws EntityNotFoundException
     */
    public Customer findOrDie(int customerId) throws EntityNotFoundException {
        Customer customer = em.find(Customer.class, customerId);
        if (Objects.isNull(customer)) {
            throw new EntityNotFoundException(
                    String.format("Can't find Customer for ID %d", customerId));
        }
        return customer;
    }

    /**
     * Save {@link Customer} in database.
     * @param customer
     */
    public void save(Customer customer) {
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
    }

    /**
     * Delete {@link Customer} from database by customer_id.
     * @param customer
     */
    public void delete(Customer customer) {
        em.getTransaction().begin();
        em.remove(customer);
        em.getTransaction().commit();
    }
}

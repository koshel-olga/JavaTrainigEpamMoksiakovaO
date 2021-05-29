package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.domain.entity.Supplier;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;

/**
 * class for work with table supplier in Database.
 */
@RequiredArgsConstructor
public class SupplierRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("javatraining-unit");
    private EntityManager em = emf.createEntityManager();

    /**
     * Find {@link Supplier} in database by id.
     * @param supplierId identification of supplier.
     * @return {@link Supplier}.
     * @throws EntityNotFoundException
     */
    public Supplier findOrDie(int supplierId) throws EntityNotFoundException {
        Supplier supplier = em.find(Supplier.class, supplierId);
        if (Objects.isNull(supplier)) {
            throw new EntityNotFoundException(
                    String.format("Can't find Supplier for ID %d", supplierId));
        }
        return supplier;
    }

    /**
     * Get collection {@link Supplier} in database.
     * @return list of Supplier.
     */
    public List<Supplier> findAll() {
        List<Supplier> suppliers = em.createQuery("Select a From Supplier a", Supplier.class).getResultList();
        return suppliers;
    }

    /**
     * Save {@link Supplier} in database.
     * @param supplier
     */
    public void save(Supplier supplier) {
        em.getTransaction().begin();
        em.persist(supplier);
        em.getTransaction().commit();
    }

    /**
     * Delete {@link Supplier} from database by supplier_id.
     * @param supplier
     */
    public void delete(Supplier supplier) {
        em.getTransaction().begin();
        em.remove(supplier);
        em.getTransaction().commit();
    }
}

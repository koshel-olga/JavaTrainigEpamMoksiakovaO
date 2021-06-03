package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;

/**
 * class for work with table supplier in Database.
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplierRepositoryImpl {

    private EntityManager entityManager;

    /**
     * Find {@link Supplier} in database by id.
     * @param supplierId identification of supplier.
     * @return {@link Supplier}.
     * @throws EntityNotFoundException
     */
    public Supplier findOrDie(int supplierId) throws EntityNotFoundException {
        Supplier supplier = entityManager.find(Supplier.class, supplierId);
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
        List<Supplier> suppliers = entityManager.createQuery("Select a From Supplier a", Supplier.class).getResultList();
        return suppliers;
    }

    /**
     * Save {@link Supplier} in database.
     * @param supplier
     */
    public void save(Supplier supplier) {
        entityManager.getTransaction().begin();
        entityManager.persist(supplier);
        entityManager.getTransaction().commit();
    }

    /**
     * Delete {@link Supplier} from database by supplier_id.
     * @param supplier
     */
    public void delete(Supplier supplier) {
        entityManager.getTransaction().begin();
        entityManager.remove(supplier);
        entityManager.getTransaction().commit();
    }
}

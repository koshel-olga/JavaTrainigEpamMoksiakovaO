package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Supplier;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface SupplierRepository {

    /**
     * Find {@link Supplier} in database by id.
     * @param supplierId identification of supplier.
     * @return {@link Supplier}.
     * @throws EntityNotFoundException
     */
    Supplier findOrDie(int supplierId) throws EntityNotFoundException;

    /**
     * Get collection {@link Supplier} in database.
     * @return list of Supplier.
     */
    List<Supplier> findAll();

    /**
     * Save {@link Supplier} in database.
     * @param supplier
     */
    void save(Supplier supplier);

    /**
     * Delete {@link Supplier} from database by supplier_id.
     * @param supplier
     */
    void delete(Supplier supplier);
}

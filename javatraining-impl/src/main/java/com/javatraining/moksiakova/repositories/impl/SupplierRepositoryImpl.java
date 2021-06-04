package com.javatraining.moksiakova.repositories.impl;

import com.javatraining.moksiakova.domain.entity.Supplier;
import com.javatraining.moksiakova.repositories.SupplierRepository;
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
@RequiredArgsConstructor
public class SupplierRepositoryImpl implements SupplierRepository {

    private EntityManager entityManager;

    @Override
    public Supplier findOrDie(int supplierId) throws EntityNotFoundException {
        Supplier supplier = entityManager.find(Supplier.class, supplierId);
        if (Objects.isNull(supplier)) {
            throw new EntityNotFoundException(
                    String.format("Can't find Supplier for ID %d", supplierId));
        }
        return supplier;
    }

    @Override
    public List<Supplier> findAll() {
        List<Supplier> suppliers = entityManager.createQuery("Select a From Supplier a", Supplier.class).getResultList();
        return suppliers;
    }

    @Override
    public void save(Supplier supplier) {
        entityManager.getTransaction().begin();
        entityManager.persist(supplier);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Supplier supplier) {
        entityManager.getTransaction().begin();
        entityManager.remove(supplier);
        entityManager.getTransaction().commit();
    }
}

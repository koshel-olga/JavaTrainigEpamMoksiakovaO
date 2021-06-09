package com.javatraining.moksiakova.repositories;

import com.javatraining.moksiakova.domain.entity.Supplier;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}

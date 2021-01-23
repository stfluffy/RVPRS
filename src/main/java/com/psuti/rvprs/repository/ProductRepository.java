package com.psuti.rvprs.repository;

import com.psuti.rvprs.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Modenov D.A.
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

package com.company.gamestore.repository;

import com.company.gamestore.models.Fee;
import com.company.gamestore.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Integer> {
    Fee findByProductType(String product_type);
}

package com.company.gamestore.repository;
import com.company.gamestore.models.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer>{
    List<Console> findByManufacturer(String manufacturer);
}

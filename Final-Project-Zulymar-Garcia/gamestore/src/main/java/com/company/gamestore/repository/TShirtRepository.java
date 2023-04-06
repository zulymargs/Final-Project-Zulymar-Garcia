package com.company.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.company.gamestore.models.TShirt;

import java.util.List;

@Repository
public interface TShirtRepository extends JpaRepository<TShirt, Integer> {

List<TShirt> findAllTShirtsByColor(String color);
List<TShirt> findAllTShirtsBySize(String size);


}

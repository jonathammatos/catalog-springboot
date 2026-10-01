package com.spring.catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.catalog.entities.Category;

@Repository //componente injetavel pelo mecanismo de injeção de dependência
public interface CategoryRepository extends JpaRepository<Category, Long>{

}

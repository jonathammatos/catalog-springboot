package com.spring.catalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.catalog.dto.CategoryDTO;
import com.spring.catalog.entities.Category;
import com.spring.catalog.repositories.CategoryRepository;

//injeção de dependência
@Service // registra a classe como componente que vai participar do sistema de gestão de
			// dependência
public class CategoryService {

	@Autowired // instancia o objeto repository
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		
		return list.stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
				
	}

}

package com.spring.catalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.catalog.dto.CategoryDTO;
import com.spring.catalog.entities.Category;
import com.spring.catalog.repositories.CategoryRepository;
import com.spring.catalog.services.exceptions.DatabaseException;
import com.spring.catalog.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

//injeção de dependência
@Service // registra a classe como componente que vai participar do sistema de gestão de
			// dependência
public class CategoryService {

	@Autowired // instancia o objeto repository
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageResquest) {
		Page<Category> list = repository.findAll(pageResquest);

		return list.map(category -> new CategoryDTO(category));

	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity Not Found!"));
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		
		try {
		Category entity = repository.getReferenceById(id);
		entity.setName(dto.getName());
		entity = repository.save(entity);
		
		return new CategoryDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found "+id+" !");
		}
	}

	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found!");
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity Violation!");
		}
		
	}
}

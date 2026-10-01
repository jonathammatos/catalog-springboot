package com.spring.catalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.catalog.entities.Category;
import com.spring.catalog.repositories.CategoryRepository;


//injeção de dependência
@Service //registra a classe como componente que vai participar do sistema de gestão de dependência
public class CategoryService {
	
	@Autowired  //instancia o objeto repository
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}

}

package com.localme.api.vo.impl;



import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.localme.api.dao.CategoryRepo;
import com.localme.api.service.CategoryService;
import com.localme.api.vo.Category;

@Transactional
@Component
public class CatgeoryImpl implements CategoryService {

	@Autowired
	 private CategoryRepo catRepo;

	@Override
	 public Iterable<Category> findAllCategories() {
	   return catRepo.findAll();
	 }
}

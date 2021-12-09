package com.localme.api.vo.impl;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.localme.api.dao.CategoryRepo;
import com.localme.api.exception.BusinessException;
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
	public Category checkCat(Category catDetail)
	{
		Category cat=catRepo.findById(catDetail.getId());
		try {
		if(cat==null)
		{
			throw new BusinessException("404","Enter a valid category");
		}
	
	}
		catch (Exception e) {
			throw new BusinessException("603","Something went wrong in Service layer while saving the employee");
		}
		return cat;
}
}

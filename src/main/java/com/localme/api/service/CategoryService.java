package com.localme.api.service;

import org.springframework.stereotype.Service;
import com.localme.api.vo.Category;



@Service
public interface CategoryService {
	Iterable<Category> findAllCategories();		
	public Category checkCat(Category catDetail);
}

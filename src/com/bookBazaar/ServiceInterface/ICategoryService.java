package com.bookBazaar.ServiceInterface;

import java.util.List;

import com.bookBazaar.entity.Category;

public interface ICategoryService {
	List<Category> getAllCategories();
	boolean addCategory(Category category);
	Category getCategoryById(int categoryId);
	boolean updateCategory(int categoryId, String name);
	boolean isValidCategoryId(int categoryId);
}

package com.bookBazaar.daoInterface;

import java.util.List;

import com.bookBazaar.entity.Category;

public interface ICategoryDao {
	List<Category> getAllCategories();
	boolean addCategory(Category category);
	Category getCategoryById(int categoryId);
	boolean updateCategory(int categoryId, String name);
}

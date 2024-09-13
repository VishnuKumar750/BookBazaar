package com.bookBazaar.service;

import java.util.List;

import com.bookBazaar.ServiceInterface.ICategoryService;
import com.bookBazaar.daoImp.CategoryDao;
import com.bookBazaar.daoInterface.ICategoryDao;
import com.bookBazaar.entity.Category;

public class CategoryService implements ICategoryService {

    private final ICategoryDao categoryDao;

    public CategoryService() {
        this.categoryDao = new CategoryDao();
    }
    
    @Override
    public boolean isValidCategoryId(int categoryId) {
        return categoryDao.getCategoryById(categoryId) != null;
    }

    // Fetch all categories
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    // Add a new category
    public boolean addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    // Fetch a category by its ID
    public Category getCategoryById(int categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

    // Delete a category
//    public boolean deleteCategory(int categoryId) {
//        return categoryDao.deleteCategory(categoryId);
//    }

    // Update a category
    public boolean updateCategory(int categoryId, String name) {
        return categoryDao.updateCategory(categoryId, name);
    }
}

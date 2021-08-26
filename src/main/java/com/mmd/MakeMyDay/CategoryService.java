package com.mmd.MakeMyDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> findAllCategories() {
        return (List<Category>)categoryRepository.findAll();
    }

    public List<Category> findAllCategoriesByActivity(Long id) {
        return categoryRepository.findByActivities_Id(id);
    }

    public Category findCategoryById(int id) {
        return categoryRepository.findById(id).get();
    }

    public void saveCategory(Category category) { categoryRepository.save(category); }
}

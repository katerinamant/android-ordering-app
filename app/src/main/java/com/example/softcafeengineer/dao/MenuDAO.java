package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.domain.Table;

import java.util.List;

public interface MenuDAO {
    public Product findProduct(String name);
    public List<Product> findAllProducts(String cafeteria_brand);
    public boolean productExists(String name);
    public void saveProduct(Product product);
    public void deleteProduct(Product product);
    public void updateProduct(String old_name, String new_name);
    public ProductCategory findCategory(String name);
    public List<ProductCategory> findAllCategories(String cafeteria_brand);
    public boolean categoryExists(String name);
    public void saveCategory(ProductCategory category);
    public void deleteCategory(ProductCategory category);
    public void updateCategory(String old_name, String new_name);
}

package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;

import java.util.List;

public interface MenuDAO
{
    /**
     * Check if a product
     * name is used within a cafeteria
     */
    public boolean productExists(String cafe_brand, String product_name);

    /**
     * Add new product
     * of a cafeteria
     */
    public void saveProduct(Product product);

    /**
     * Remove a product
     * from a cafeteria
     */
    public void deleteProduct(Product product);

    /**
     * Find product based
     * on name within a cafeteria
     */
    public Product findProduct(String cafe_brand, String product_name);

    /**
     * Find all products
     * of a cafeteria
     */
    public List<Product> findAllProducts(String cafeteria_brand);

    /**
     * Check if a product category
     * name is used within a cafeteria
     */
    public boolean categoryExists(String cafe_brand, String category_name);

    /**
     * Add new product category
     * of a cafeteria
     */
    public void saveCategory(ProductCategory category);

    /**
     * Remove a product category
     * from a cafeteria
     */
    public void deleteCategory(ProductCategory category);


    /**
     * Find product category
     * based on name within
     * a cafeteria
     */
    public ProductCategory findCategory(String cafe_brand, String category_name);

    /**
     * Find all categories
     * of a cafeteria
     */
    public List<ProductCategory> findAllCategories(String cafeteria_brand);

    /**
     * Changes key when cafeteria
     * changes brand
     */
    void updateCafeteria(String prev_brand, String new_brand);
}

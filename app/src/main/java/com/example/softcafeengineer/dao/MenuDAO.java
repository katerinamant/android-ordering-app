package com.example.softcafeengineer.dao;

import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;

import java.util.List;

public interface MenuDAO {
    /**
     * Check if a product
     * name is used within a cafeteria
     */
    boolean productExists(String cafeteria_brand, String product_name);

    /**
     * Add new product
     * of a cafeteria
     */
    void saveProduct(Product product);

    /**
     * Remove a product
     * from a cafeteria
     */
    void deleteProduct(Product product);

    /**
     * Find product based
     * on name within a cafeteria
     */
    Product findProduct(String cafeteria_brand, String product_name);

    /**
     * Find all products
     * of a cafeteria
     */
    List<Product> findAllProducts(String cafeteria_brand);

    /**
     * Find all products
     * of a cafeteria
     * belonging to a
     * specific category
     */
    List<Product> findAllProductsOfCategory(String cafeteria_brand, ProductCategory category);

    /**
     * Check if a product category
     * name is used within a cafeteria
     */
    boolean categoryExists(String cafeteria_brand, String category_name);

    /**
     * Add new product category
     * of a cafeteria
     */
    void saveCategory(ProductCategory category);

    /**
     * Remove a product category
     * from a cafeteria
     */
    void deleteCategory(ProductCategory category);


    /**
     * Find product category
     * based on name within
     * a cafeteria
     */
    ProductCategory findCategory(String cafeteria_brand, String category_name);

    /**
     * Find all categories
     * of a cafeteria
     */
    List<ProductCategory> findAllCategories(String cafeteria_brand);

    /**
     * Changes key when cafeteria
     * changes brand
     */
    void updateCafeteria(String old_brand, String new_brand);
}

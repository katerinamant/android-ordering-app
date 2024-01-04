package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.domain.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuDAOMemory implements MenuDAO
{
    protected static List<Product> products = new ArrayList<Product>();
    protected static HashMap<String, Product> name_to_product = new HashMap<String, Product>();
    protected static HashMap<String, ArrayList<Product>> cafeteria_to_products = new HashMap<String, ArrayList<Product>>();
    protected  static List<ProductCategory> categories = new ArrayList<ProductCategory>();
    protected static HashMap<String, ProductCategory> name_to_category = new HashMap<String, ProductCategory>();
    protected static HashMap<String, ArrayList<ProductCategory>> cafeteria_to_categories = new HashMap<String, ArrayList<ProductCategory>>();
    @Override
    public Product findProduct(String name)
    {
        if (name_to_product.containsKey(name)) {
            return name_to_product.get(name);
        }
        return null;
    }
    @Override
    public ProductCategory findCategory(String name)
    {
        if (name_to_category.containsKey(name)) {
            return name_to_category.get(name);
        }
        return null;
    }
    @Override
    public List<Product> findAllProducts(String cafeteria_brand)
    {
        if(cafeteria_to_products.containsKey(cafeteria_brand)){
            return cafeteria_to_products.get(cafeteria_brand);
        }
        return new ArrayList<>();
    }
    @Override
    public List<ProductCategory> findAllCategories(String cafeteria_brand)
    {
        if(cafeteria_to_categories.containsKey(cafeteria_brand)) {
            return cafeteria_to_categories.get(cafeteria_brand);
        }
        return new ArrayList<>();
    }
    @Override
    public boolean productExists(String name)
    {
        return name_to_product.containsKey(name);
    }
    @Override
    public boolean categoryExists(String name)
    {
        return name_to_category.containsKey(name);
    }
    @Override
    public void saveProduct(Product product)
    {
        products.add(product);
        name_to_product.put(product.getName(), product);

        String brand_key = product.getCafe().getBrand();
        if(cafeteria_to_products.containsKey(brand_key)){
            cafeteria_to_products.get(brand_key).add(product);
        } else {
            ArrayList<Product> products = new ArrayList<Product>();
            products.add(product);
            cafeteria_to_products.put(brand_key, products);
        }
    }
    @Override
    public void saveCategory(ProductCategory category)
    {
        categories.add(category);
        name_to_category.put(category.getName(), category);
        String brand_key = category.getCafe().getBrand();
        if(cafeteria_to_categories.containsKey(brand_key)) {
            cafeteria_to_categories.get(brand_key).add(category);
        } else {
            ArrayList<ProductCategory> categories = new ArrayList<ProductCategory>();
            categories.add(category);
            cafeteria_to_categories.put(brand_key, categories);
        }
    }
    @Override
    public void deleteProduct(Product product){
        name_to_product.remove(product.getName());
        cafeteria_to_products.get(product.getCafe().getBrand()).remove(product);
        products.remove(product);
    }
    @Override
    public void deleteCategory(ProductCategory category){
        name_to_category.remove(category.getName());
        cafeteria_to_categories.get(category.getCafe().getBrand()).remove(category);
        categories.remove(category);
    }
    @Override
    public void updateProduct(String old_name, String new_name){
        Product product = name_to_product.get(old_name);
        name_to_product.remove(old_name);
        product.setName(new_name);
        name_to_product.put(new_name, product);
    }
    @Override
    public void updateCategory(String old_name, String new_name){
        ProductCategory category = name_to_category.get(old_name);
        name_to_category.remove(old_name);
        category.setName(new_name);
        name_to_category.put(new_name, category);
    }
}

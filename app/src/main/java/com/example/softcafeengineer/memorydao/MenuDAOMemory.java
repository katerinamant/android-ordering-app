package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuDAOMemory implements MenuDAO
{
    protected static HashMap<String, ArrayList<Product>> cafeteria_to_products = new HashMap<String, ArrayList<Product>>();
    protected static HashMap<String, ArrayList<ProductCategory>> cafeteria_to_categories = new HashMap<String, ArrayList<ProductCategory>>();

    @Override
    public boolean productExists(String cafe_brand, String product_name) {
        if(cafeteria_to_products.containsKey(cafe_brand)) {
            ArrayList<Product> products = cafeteria_to_products.get(cafe_brand);
            for (Product p : products) {
                if (p.getName().equals(product_name)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void saveProduct(Product product) {
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
    public void deleteProduct(Product product) {
        cafeteria_to_products.get(product.getCafe().getBrand()).remove(product);
    }

    @Override
    public Product findProduct(String cafe_brand, String product_name) {
        ArrayList<Product> products = cafeteria_to_products.get(cafe_brand);
        for(Product p : products) {
            if(p.getName().equals(product_name)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Product> findAllProducts(String cafeteria_brand) {
        if(cafeteria_to_products.containsKey(cafeteria_brand)){
            return cafeteria_to_products.get(cafeteria_brand);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean categoryExists(String cafe_brand, String category_name) {
        if(cafeteria_to_categories.containsKey(cafe_brand)) {
            ArrayList<ProductCategory> categories = cafeteria_to_categories.get(cafe_brand);
            for(ProductCategory p_c : categories) {
                if(p_c.getName().equals(category_name)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void saveCategory(ProductCategory category) {
        String brand_key = category.getCafe().getBrand();
        if(cafeteria_to_categories.containsKey(brand_key)){
            cafeteria_to_categories.get(brand_key).add(category);
        } else {
            ArrayList<ProductCategory> categories = new ArrayList<ProductCategory>();
            categories.add(category);
            cafeteria_to_categories.put(brand_key, categories);
        }
    }

    @Override
    public void deleteCategory(ProductCategory category) {
        cafeteria_to_categories.get(category.getCafe().getBrand()).remove(category);
    }

    @Override
    public ProductCategory findCategory(String cafe_brand, String category_name) {
        ArrayList<ProductCategory> categories = cafeteria_to_categories.get(cafe_brand);
        for(ProductCategory p_c : categories) {
            if(p_c.getName().equals(category_name)) {
                return p_c;
            }
        }
        return null;
    }

    @Override
    public List<ProductCategory> findAllCategories(String cafeteria_brand) {
        if(cafeteria_to_categories.containsKey(cafeteria_brand)) {
            return cafeteria_to_categories.get(cafeteria_brand);
        }
        return new ArrayList<>();
    }

    @Override
    public void updateCafeteria(String old_brand, String new_brand) {
        if(cafeteria_to_products.containsKey(old_brand)) {
            ArrayList<Product> products  = cafeteria_to_products.get(old_brand);
            cafeteria_to_products.remove(old_brand);
            cafeteria_to_products.put(new_brand, products);
        } else {
            ArrayList<Product> products = new ArrayList<Product>();
            cafeteria_to_products.put(new_brand, products);
        }
        if(cafeteria_to_categories.containsKey(old_brand)) {
            ArrayList<ProductCategory> categories  = cafeteria_to_categories.get(old_brand);
            cafeteria_to_categories.remove(old_brand);
            cafeteria_to_categories.put(new_brand, categories);
        } else {
            ArrayList<ProductCategory> categories = new ArrayList<ProductCategory>();
            cafeteria_to_categories.put(new_brand, categories);
        }
    }
}

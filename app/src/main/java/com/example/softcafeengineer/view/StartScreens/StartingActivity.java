package com.example.softcafeengineer.view.StartScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.softcafeengineer.R;
import com.example.softcafeengineer.dao.BaristaDAO;
import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.ManagerDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.dao.RevenueDAO;
import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Barista;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Product;
import com.example.softcafeengineer.domain.ProductCategory;
import com.example.softcafeengineer.domain.Table;
import com.example.softcafeengineer.domain.User;
import com.example.softcafeengineer.memorydao.BaristaDAOMemory;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.ManagerDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;
import com.example.softcafeengineer.memorydao.RevenueDAOMemory;
import com.example.softcafeengineer.memorydao.TableDAOMemory;
import com.example.softcafeengineer.view.Manager.SignUp.ManagerSignUpActivity;

public class StartingActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        // Set up demo cafeteria
        CafeteriaDAO cafeteriaDAO = new CafeteriaDAOMemory();
        RevenueDAO revenueDAO = new RevenueDAOMemory();
        ManagerDAO userDAO = new ManagerDAOMemory();
        BaristaDAO baristaDAO = new BaristaDAOMemory();
        TableDAO tableDAO = new TableDAOMemory();
        MenuDAO menuDAO = new MenuDAOMemory();

        // Create cafeteria
        Cafeteria cafe = new Cafeteria("Cafe Str 5", "2100000000", "123456789", "Our Cafeteria");
        cafeteriaDAO.save(cafe);
        revenueDAO.addCafeteria(cafe.getBrand());

        // Create manager of cafeteria
        User admin = new User("admin", "12345678");
        admin.setCafe(cafe);
        userDAO.save(admin);

        // Create baristas of cafeteria
        Barista barista = new Barista("katerinamant", "12345678");
        barista.setCafe(cafe);
        baristaDAO.save(barista);

        barista = new Barista("reasklika", "12345678");
        barista.setCafe(cafe);
        baristaDAO.save(barista);

        barista = new Barista("sofiasotiriou", "12345678");
        barista.setCafe(cafe);
        baristaDAO.save(barista);

        // Create tables of cafeteria
        Table table = new Table("table1", 1, cafe);
        tableDAO.save(table);

        table = new Table("table2", 2, cafe);
        tableDAO.save(table);

        table = new Table("table3", 3, cafe);
        tableDAO.save(table);

        // Create menu of cafeteria
        ProductCategory coffees = new ProductCategory("Coffees", "Hot and cold caffeinated beverages ☕", cafe);
        menuDAO.saveCategory(coffees);

        Product product = new Product(1.5, "Espresso", true, coffees, cafe);
        menuDAO.saveProduct(product);

        product = new Product(2.1, "Freddo Espresso", true, coffees, cafe);
        menuDAO.saveProduct(product);

        product = new Product(2, "Cappuccino", true, coffees, cafe);
        menuDAO.saveProduct(product);

        product = new Product(2.5, "Freddo Cappuccino", true, coffees, cafe);
        menuDAO.saveProduct(product);

        product = new Product(1.8, "Frappe", false, coffees, cafe);
        menuDAO.saveProduct(product);

        product = new Product(1.8, "Greek Coffee", false, coffees, cafe);
        menuDAO.saveProduct(product);

        ProductCategory beverages = new ProductCategory("Beverages", "Various beverages 🍵", cafe);
        menuDAO.saveCategory(beverages);

        product = new Product(2.5, "Hot Chocolate", true, beverages, cafe);
        menuDAO.saveProduct(product);

        product = new Product(3, "Fresh Orange Juice", true, beverages, cafe);
        menuDAO.saveProduct(product);

        product = new Product(2.5, "Lemon Juice", false, beverages, cafe);
        menuDAO.saveProduct(product);

        product = new Product(1.9, "Black tea", true, beverages, cafe);
        menuDAO.saveProduct(product);

        product = new Product(1.5, "Coca cola", true, beverages, cafe);
        menuDAO.saveProduct(product);

        product = new Product(0.5, "Water 500ml", true, beverages, cafe);
        menuDAO.saveProduct(product);

        ProductCategory brunch = new ProductCategory("Brunch", "Available 10am - 4pm 🥞", cafe);
        menuDAO.saveCategory(brunch);

        product = new Product(8.8, "Croque Madame", true, brunch, cafe);
        menuDAO.saveProduct(product);

        product = new Product(8.4, "Avocado Toast", true, brunch, cafe);
        menuDAO.saveProduct(product);

        product = new Product(7.8, "Italian Omelette", true, brunch, cafe);
        menuDAO.saveProduct(product);

        product = new Product(7.8, "Savory Pancakes", true, brunch, cafe);
        menuDAO.saveProduct(product);

        product = new Product(8.8, "Pancakes with chocolate", false, brunch, cafe);
        menuDAO.saveProduct(product);

        ProductCategory sandwiches = new ProductCategory("Sandwiches", "Sandwiches - Club sandwiches 🥪", cafe);
        menuDAO.saveCategory(sandwiches);

        product = new Product(5, "Chicken Sandwich", true, sandwiches, cafe);
        menuDAO.saveProduct(product);

        product = new Product(9, "Chicken Club Sandwich", true, sandwiches, cafe);
        menuDAO.saveProduct(product);

        product = new Product(9, "Salmon Club Sandwich", true, sandwiches, cafe);
        menuDAO.saveProduct(product);

        Intent intent = new Intent(StartingActivity.this, WelcomeScreenActivity.class);
        startActivity(intent);
    }
}

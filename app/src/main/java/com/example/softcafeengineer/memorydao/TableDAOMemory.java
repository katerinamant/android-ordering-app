package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Table;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TableDAOMemory implements TableDAO
{
    protected static List<Table> tables = new ArrayList<Table>();
    protected static HashMap<String, Table> id_to_table = new HashMap<String, Table>();
    protected static HashMap<String, ArrayList<Table>> cafeteria_to_tables = new HashMap<String, ArrayList<Table>>();

    @Override
    public Table find(String unique_id) {
        if(id_to_table.containsKey(unique_id)) {
            return id_to_table.get(unique_id);
        }
        return null;
    }

    @Override
    public List<Table> findAll(String cafeteria_brand) {
        if(cafeteria_to_tables.containsKey(cafeteria_brand)) {
            return cafeteria_to_tables.get(cafeteria_brand);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean exists(String unique_id) {
        return id_to_table.containsKey(unique_id);
    }

    @Override
    public void save(Table table) {
        // No need to check if id is in use
        // As long as we use the exists method first
        tables.add(table);
        id_to_table.put(table.getQRCode(), table);

        String brand_key = table.getCafe().getBrand();
        if(cafeteria_to_tables.containsKey(brand_key)) {
            cafeteria_to_tables.get(brand_key).add(table);
        } else {
            ArrayList<Table> tables = new ArrayList<Table>();
            tables.add(table);
            cafeteria_to_tables.put(brand_key, tables);
        }
    }

    @Override
    public void delete(Table table) {
        id_to_table.remove(table.getQRCode());
        cafeteria_to_tables.get(table.getCafe().getBrand()).remove(table);
        tables.remove(table);
    }
}

package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableDAOMemory implements TableDAO
{
    protected static List<Table> tables = new ArrayList<Table>();
    protected static HashMap<String, Table> unique_id_to_table = new HashMap<String, Table>();
    protected static HashMap<String, ArrayList<Table>> cafeteria_to_tables = new HashMap<String, ArrayList<Table>>();

    @Override
    public Table find(String unique_id) {
        if(unique_id_to_table.containsKey(unique_id)) {
            return unique_id_to_table.get(unique_id);
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
        return unique_id_to_table.containsKey(unique_id);
    }

    @Override
    public void save(Table table) {
        // No need to check if id is in use
        // As long as we use the exists method first
        tables.add(table);
        unique_id_to_table.put(table.getQRCode(), table);

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
        unique_id_to_table.remove(table.getQRCode());
        cafeteria_to_tables.get(table.getCafe().getBrand()).remove(table);
        tables.remove(table);
    }

    @Override
    public void updateTable(String old_unique_id, String new_unique_id) {
        Table table = unique_id_to_table.get(old_unique_id);
        unique_id_to_table.remove(old_unique_id);
        table.setQRCode(new_unique_id);
        unique_id_to_table.put(new_unique_id, table);
    }

    @Override
    public void updateCafeteria(String old_brand, String new_brand) {
        ArrayList<Table> tables = cafeteria_to_tables.get(old_brand);
        cafeteria_to_tables.remove(old_brand);
        cafeteria_to_tables.put(new_brand, tables);
    }
}

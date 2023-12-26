package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Table;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TableDAOMemory implements TableDAO
{
    protected static List<Table> tables = new ArrayList<Table>();

    @Override
    public Table find(String id) {
        for(Table t : tables) {
            if(t.getQRCode().equals(id)) {
                return t;
            }
        }
        // Invalid id
        return null;
    }

    @Override
    public List<Table> findAll(String cafeteria_brand) {
        List<Table> result = new ArrayList<>();
        for(Table t : tables) {
            if (t.getCafe().getBrand().equals(cafeteria_brand)) {
                result.add(t);
            }
        }
        return result;
    }

    @Override
    public boolean save(Table table) {
        if(tables.contains(table)) return false;

        // Table is not in list
        // Each table must have a unique id
        for(Table t : tables) {
            if(t.getQRCode().equals(table.getQRCode())) {
                // Cannot add table due to id conflict
                return false;
            }
        }
        tables.add(table);
        return true;
    }

    @Override
    public void delete(Table table) { tables.remove(table); }
}

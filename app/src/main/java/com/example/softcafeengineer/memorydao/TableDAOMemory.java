package com.example.softcafeengineer.memorydao;

import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Table;

import java.util.ArrayList;
import java.util.List;

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

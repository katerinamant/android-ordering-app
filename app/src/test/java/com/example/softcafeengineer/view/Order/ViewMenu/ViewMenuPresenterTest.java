package com.example.softcafeengineer.view.Order.ViewMenu;
import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.MenuDAO;
import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Table;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.MenuDAOMemory;
import com.example.softcafeengineer.memorydao.TableDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ViewMenuPresenterTest {
    private MenuDAO menuDAO;
    private TableDAO tableDAO;
    private Table table;
    private ViewMenuViewStub view;
    private ViewMenuPresenter presenter;
    private Cafeteria cafeteria;
    @Before
    public void setUp(){
        menuDAO = new MenuDAOMemory();
        tableDAO = new TableDAOMemory();
        view = new ViewMenuViewStub();
        cafeteria = new Cafeteria("address", "0123456789", "123456789","cafe_brand");
        table = new Table("QR", 1, cafeteria);
        tableDAO.save(table);


        presenter = new ViewMenuPresenter();
        presenter.setMenuDAO(menuDAO);
        presenter.setTableDAO(tableDAO);
        presenter.setView(view, table.getQRCode());
    }
    @Test
    public void testGetMenuDAO(){
        Assert.assertEquals(presenter.getMenuDAO(), menuDAO);
    }

    @Test
    public void testGetTableDAO(){
        Assert.assertEquals(presenter.getTableDAO(), tableDAO);
    }
    @Test
    public void testGetCategoryResults(){
        Assert.assertEquals(presenter.getCategoryResults(), menuDAO.findAllCategories(cafeteria.getBrand()));
    }
}

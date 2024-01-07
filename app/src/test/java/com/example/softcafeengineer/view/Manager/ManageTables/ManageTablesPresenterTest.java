package com.example.softcafeengineer.view.Manager.ManageTables;

import com.example.softcafeengineer.dao.CafeteriaDAO;
import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Table;
import com.example.softcafeengineer.memorydao.CafeteriaDAOMemory;
import com.example.softcafeengineer.memorydao.TableDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManageTablesPresenterTest {
    private ManageTablesPresenter presenter;
    private ManageTablesViewStub view;
    private TableDAO tableDAO;
    private CafeteriaDAO cafeteriaDAO;
    private Cafeteria cafe;
    /**
     *  initializing all the objects needed to run tests for the
     *  ManageTablesPresenter methods
     */
    @Before
    public void setUp(){
        view = new ManageTablesViewStub();
        tableDAO = new TableDAOMemory();
        cafeteriaDAO = new CafeteriaDAOMemory();
        cafe = new Cafeteria();
        cafe.setBrand("cafe_brand");
        cafeteriaDAO.save(cafe);

        presenter = new ManageTablesPresenter();
        presenter.setCafeteriaDAO(cafeteriaDAO);
        presenter.setView(view);
        presenter.setTableDAO(tableDAO);
        presenter.setBrand(cafeteriaDAO.find("cafe_brand").getBrand());
    }

    /**
     *  testing whether the onAddNewTable method shoes the correct error title and error
     *  message when the manager tries to add a QR code that is already assigned to another table
     */
    @Test
    public void testAddExistingQRCode(){
        presenter.onAddNewTable(true, "1", "existingQR");
        presenter.onAddNewTable(true, "12", "existingQR");
        Assert.assertEquals(view.getErrorTitle(), "Unique id is taken.");
        Assert.assertEquals(view.getErrorMessage(),"Please provide a different unique id.");
    }
    /**
     *  testing whether the onAddNewTable method shows the correct toast
     *  message when the add button is disabled, meaning some of the
     *  required fields were left empty
     */
    @Test
    public void testDisabledAddButton(){
        presenter.onAddNewTable(false, "","");
        Assert.assertEquals(view.getToastMessage(), "Please fill all the required fields.");
    }
    /**
     *  testing whether the onEditTable method shoes the correct error title and error
     *  message when the manager tries to change a QR code to one that is already assigned to another table
     */
    @Test
    public void testEditExistingQRCode(){
        presenter.onAddNewTable(true, "1","edit_QR");
        presenter.onAddNewTable(true, "2", "prev_QR");
        presenter.onEditTable(new Table(), true, true, Integer.parseInt("2"),"prev_QR", "2","edit_QR");
        Assert.assertEquals(view.getErrorTitle(), "Unique id is taken.");
        Assert.assertEquals(view.getErrorMessage(), "Please provide a different unique id.");
    }
    /**
     *  testing whether the onEditTable method shows the correct toast
     *  message when the confirm button is disabled, meaning some of the
     *  required fields were left empty
     */
    @Test
    public void testDisabledEditButton(){
        presenter.onEditTable(new Table(), false, true, 5,"","","");
        Assert.assertEquals(view.getToastMessage(),"Please fill all the required fields.");
    }
    /**
     *  testing whether the method getTableDAO returns the correct
     *  TableDAO object
     */
    @Test
    public void testGetTableDAO(){
        Assert.assertEquals(presenter.getTableDAO(), tableDAO);
    }
    /**
     *  testing whether the method getCafeteriaDAO returns the correct
     *  CafeteriaDAO object
     */
    @Test
    public void testGetCafeteriaDAO(){
        Assert.assertEquals(presenter.getCafeteriaDAO(),cafeteriaDAO);
    }
    /**
     *  testing whether the method getBrand returns the correct
     *  brand of this cafeteria
     */
    @Test
    public void testGetBrand(){
        Assert.assertEquals(presenter.getBrand(), cafe.getBrand());
    }
    /**
     *  testing whether the method getTableResults returns all the tables
     *  this cafeteria has
     */
    @Test
    public void testGetTableResults(){
        Assert.assertEquals(presenter.getTableResults(), tableDAO.findAll(cafe.getBrand()));
    }
}

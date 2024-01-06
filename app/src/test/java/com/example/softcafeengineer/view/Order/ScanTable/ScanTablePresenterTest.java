package com.example.softcafeengineer.view.Order.ScanTable;
import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.Table;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.TableDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ScanTablePresenterTest {
    private TableDAO tables;
    private ActiveOrdersDAO orders;
    private ScanTableViewStub view;
    private ScanTablePresenter presenter;
    private Table table;
    @Before
    public void setUp(){
        view = new ScanTableViewStub();
        tables = new TableDAOMemory();
        orders = new ActiveOrdersDAOMemory();
        table = new Table("QR", 1, new Cafeteria());
        presenter = new ScanTablePresenter(view, tables , orders);
    }
    @Test
    public void testDisabledSubmitButton(){
        presenter.onSubmit(false, "");
        Assert.assertEquals(view.getToastMessage(), "Please fill the required field.");
    }
    @Test
    public void testUnsuccesfulConnection(){
        presenter.onSubmit(true, "QR");
        Assert.assertEquals(view.getErrorTitle(), "Connection unsuccessful.");
        Assert.assertEquals(view.getErrorMessage(), "The id provided was invalid. Try again.");
    }

}

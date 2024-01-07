package com.example.softcafeengineer.view.Order.ScanTable;
import com.example.softcafeengineer.dao.ActiveCartsDAO;
import com.example.softcafeengineer.dao.ActiveOrdersDAO;
import com.example.softcafeengineer.dao.TableDAO;
import com.example.softcafeengineer.domain.Cafeteria;
import com.example.softcafeengineer.domain.InvalidDateException;
import com.example.softcafeengineer.domain.Table;
import com.example.softcafeengineer.memorydao.ActiveCartsDAOMemory;
import com.example.softcafeengineer.memorydao.ActiveOrdersDAOMemory;
import com.example.softcafeengineer.memorydao.TableDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ScanTablePresenterTest {
    private TableDAO tables;
    private ActiveOrdersDAO orders;
    private ActiveCartsDAO carts;
    private ScanTableViewStub view;
    private ScanTablePresenter presenter;
    private Table table;
    @Before
    public void setUp(){
        view = new ScanTableViewStub();
        tables = new TableDAOMemory();
        orders = new ActiveOrdersDAOMemory();
        carts = new ActiveCartsDAOMemory();
        table = new Table("QR", 1, new Cafeteria());
        presenter = new ScanTablePresenter(view, tables , orders, carts);
    }
    @Test
    public void testDisabledSubmitButton() throws InvalidDateException {
        presenter.onSubmit(false, "");
        Assert.assertEquals(view.getToastMessage(), "Please fill the required field.");
    }
    @Test
    public void testUnsuccesfulConnection() throws InvalidDateException {
        presenter.onSubmit(true, "QR");
        Assert.assertEquals(view.getErrorTitle(), "Connection unsuccessful.");
        Assert.assertEquals(view.getErrorMessage(), "The id provided was invalid. Try again.");
    }

}

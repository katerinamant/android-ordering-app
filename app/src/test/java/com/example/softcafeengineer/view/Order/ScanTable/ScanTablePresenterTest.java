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
    private ScanTableViewStub view;
    private ScanTablePresenter presenter;
    private Table table;
    private ActiveCartsDAO carts;

    /**
     * initializing all the objects needed to run tests for the
     * ScanTablePresenter methods
     */
    @Before
    public void setUp() {
        view = new ScanTableViewStub();
        tables = new TableDAOMemory();
        orders = new ActiveOrdersDAOMemory();
        table = new Table("QR", 1, new Cafeteria());
        carts = new ActiveCartsDAOMemory();
        presenter = new ScanTablePresenter(view, tables, orders, carts);
    }

    /**
     * testing whether the onSubmit method shows the correct toast
     * message when the submit button is disabled, meaning some of the
     * required fields were left empty
     *
     * @throws InvalidDateException
     */
    @Test
    public void testDisabledSubmitButton() throws InvalidDateException {
        presenter.onSubmit(false, "");
        Assert.assertEquals(view.getToastMessage(), "Please fill the required field.");
    }

    /**
     * testing whether the onSubmit method shows the correct error title and error
     * message when the QR code (id) provided does not exist
     *
     * @throws InvalidDateException
     */
    @Test
    public void testUnsuccesfulConnection() throws InvalidDateException {
        presenter.onSubmit(true, "QR");
        Assert.assertEquals(view.getErrorTitle(), "Connection unsuccessful.");
        Assert.assertEquals(view.getErrorMessage(), "The id provided was invalid. Try again.");
    }

}

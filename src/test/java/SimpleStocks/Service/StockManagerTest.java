package SimpleStocks.Service;

import SimpleStocks.Model.IStock;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

/**
 * Created by David on 05/11/2016.
 */
public class StockManagerTest {
    IStockManager stockManager;

    @Test
    public void addStock() throws Exception {
        stockManager = new StockManager();
        IStock mockedStock = mock(IStock.class);

        stockManager.AddStock(mockedStock);
        int expected = 1;
        int actual = stockManager.GetStocks().size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStocks() throws Exception {
        IStock mockedStock = mock(IStock.class);
        Collection<IStock> stocks = new ArrayList<IStock>();
        stocks.add(mockedStock);
        stockManager = new StockManager(stocks);

        Collection<IStock> expected = stocks;
        Collection<IStock> actual = stockManager.GetStocks();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStockByStockSymbol() throws Exception {
        IStock mockedStock = mock(IStock.class);
        when(mockedStock.GetStockSymbol()).thenReturn("TEA");
        Collection<IStock> stocks = new ArrayList<IStock>();
        stocks.add(mockedStock);
        stockManager = new StockManager(stocks);

        IStock expected = mockedStock;
        IStock actual = stockManager.GetStockByStockSymbol("TEA");

        Assert.assertEquals(expected, actual);
    }

}
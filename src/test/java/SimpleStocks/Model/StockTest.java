package SimpleStocks.Model;

import org.junit.Assert;

import static org.junit.Assert.*;

/**
 * Created by David on 03/11/2016.
 */
public class StockTest {
    @org.junit.Test
    public void getStockSymbol() throws Exception {
        Stock teaStock = new Stock("TEA", StockType.COMMON, 0, 100);
        String expected = "TEA";
        String actual = teaStock.GetStockSymbol();
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void getStockType() throws Exception {
        Stock teaStock = new Stock("TEA", StockType.COMMON, 0, 100);
        StockType expected = StockType.COMMON;
        StockType actual = teaStock.GetStockType();
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void getLastDividend() throws Exception {
        Stock teaStock = new Stock("TEA", StockType.COMMON, 0, 100);
        int expected = 0;
        int actual = teaStock.GetVariableDividend();
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void setLastDividend() throws Exception {
        Stock teaStock = new Stock("TEA", StockType.COMMON, 0, 100);
        teaStock.SetVariableDividend(1);
        int expected = 1;
        int actual = teaStock.GetVariableDividend();
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void getFixedDividend() throws Exception {
        Stock teaStock = new Stock("TEA", StockType.COMMON, 0, 100);
        double expected = 0;
        double actual = teaStock.GetFixedDividend();
        Assert.assertEquals(expected, actual, 0.01);
    }

    @org.junit.Test
    public void setFixedDividend() throws Exception {
        Stock teaStock = new Stock("TEA", StockType.COMMON, 0, 100);
        teaStock.SetFixedDividend(1);
        double expected = 1;
        double actual = teaStock.GetFixedDividend();
        Assert.assertEquals(expected, actual, 0.01);
    }

    @org.junit.Test
    public void getParValue() throws Exception {
        Stock teaStock = new Stock("TEA", StockType.COMMON, 0, 100);
        int expected = 100;
        int actual = teaStock.GetParValue();
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void calculateDividendYield() throws Exception
    {
        Stock teaStock = new Stock("TEA", StockType.COMMON, 0, 100);
        Stock ginStock = new Stock("GIN", StockType.PREFERRED, 8, 100, 0.02);

        double expected = 0;
        double actual = teaStock.CalculateDividendYield(1);
        assertEquals(expected, actual, 0.01);

        expected = 2.0;
        actual = ginStock.CalculateDividendYield(1);
        Assert.assertEquals(expected, actual, 0.01);
    }
}
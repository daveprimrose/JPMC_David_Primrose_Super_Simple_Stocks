package SimpleStocks.Model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.mockito.Mockito.*;

/**
 * Created by David on 03/11/2016.
 */
public class TradeTest {

    @Test
    public void getTradedStock() throws Exception {
        IStock mockedStock = mock(IStock.class);
        when(mockedStock.GetStockSymbol()).thenReturn("TEA");
        ITrade trade = new Trade(mockedStock, 10, TradeIndicator.BUY, 10.00);

        String expected = "TEA";
        String actual = trade.GetTradedStock().GetStockSymbol();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTradeTimestamp() throws Exception {
        IStock mockedStock = mock(IStock.class);
        ITrade trade = new Trade(mockedStock, 10, TradeIndicator.BUY, 10.00, new Date((1478178000000L))); // 03/11/2016 1:00PM

        Date expected = new Date((1478178000000L)); // 03/11/2016 1:00PM
        Date actual = trade.GetTradeTimestamp();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTradedQuantity() throws Exception {
        IStock mockedStock = mock(IStock.class);
        ITrade trade = new Trade(mockedStock, 10, TradeIndicator.BUY, 10.00);

        int expected = 10;
        int actual = trade.GetTradedQuantity();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTradeIndicator() throws Exception {
        IStock mockedStock = mock(IStock.class);
        ITrade trade = new Trade(mockedStock, 10, TradeIndicator.BUY, 10.00);

        TradeIndicator expected = TradeIndicator.BUY;
        TradeIndicator actual = trade.GetTradeIndicator();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTradedPrice() throws Exception {
        IStock mockedStock = mock(IStock.class);
        ITrade trade = new Trade(mockedStock, 10, TradeIndicator.BUY, 10.00);

        double expected = 10.00;
        double actual = trade.GetTradedPrice();

        Assert.assertEquals(expected, actual, 0.001);
    }

}
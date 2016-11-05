package SimpleStocks.Service;

import SimpleStocks.Model.IStock;
import SimpleStocks.Model.ITrade;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by David on 03/11/2016.
 */
public class TradeManagerTest {
    ITradeManager tradeManager;

    @Test
    public void recordTrade() throws Exception {
        tradeManager = new TradeManager();
        ITrade mockedTrade = mock(ITrade.class);
        tradeManager.RecordTrade(mockedTrade);

        int expected = 1;
        int actual = tradeManager.GetTrades().size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTrades() throws Exception {
        ITrade mockedTrade = mock(ITrade.class);
        Collection<ITrade> trades = new ArrayList<ITrade>();
        trades.add(mockedTrade);
        tradeManager = new TradeManager(trades);

        Collection<ITrade> expected = trades;
        Collection<ITrade> actual = tradeManager.GetTrades();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculateVWAP() throws Exception {
        IStock mockedStock = mock(IStock.class);
        when(mockedStock.GetStockSymbol()).thenReturn("TEA");
        ITrade firstMockedTrade = mock(ITrade.class);
        when(firstMockedTrade.GetTradedStock()).thenReturn(mockedStock);
        when(firstMockedTrade.GetTradeTimestamp()).thenReturn(new Date(System.currentTimeMillis()-(1*60*1000)));
        when(firstMockedTrade.GetTradedPrice()).thenReturn(2.0);
        when(firstMockedTrade.GetTradedQuantity()).thenReturn(15);
        ITrade secondMockedTrade = mock(ITrade.class);
        when(secondMockedTrade.GetTradedStock()).thenReturn(mockedStock);
        when(secondMockedTrade.GetTradeTimestamp()).thenReturn(new Date(System.currentTimeMillis()-(2*60*1000)));
        when(secondMockedTrade.GetTradedPrice()).thenReturn(8.0);
        when(secondMockedTrade.GetTradedQuantity()).thenReturn(5);

        Collection<IStock> stocks = new ArrayList<IStock>();
        stocks.add(mockedStock);
        Collection<ITrade> trades= new ArrayList<ITrade>();
        trades.add(firstMockedTrade);
        trades.add(secondMockedTrade);

        tradeManager = new TradeManager(trades);

        double expected = 3.5;
        double actual = tradeManager.CalculateVWSP(mockedStock);
        Assert.assertEquals(expected, actual, 0.01);
    }

    @Test
    public void calculateGeometricMeanOfStockPricesInGBCE() throws Exception {
        ITrade firstMockedTrade = mock(ITrade.class);
        when(firstMockedTrade.GetTradedPrice()).thenReturn(10.00);
        ITrade secondMockedTrade = mock(ITrade.class);
        when(secondMockedTrade.GetTradedPrice()).thenReturn(15.00);
        ITrade thirdMockedTrade = mock(ITrade.class);
        when(thirdMockedTrade.GetTradedPrice()).thenReturn(2.00);

        Collection<ITrade> tradesCollection = new ArrayList<ITrade>();
        tradesCollection.add(firstMockedTrade);
        tradesCollection.add(secondMockedTrade);
        tradesCollection.add(thirdMockedTrade);

        ITradeManager simpleStockManager = new TradeManager(tradesCollection);

        double expected = 6.69;
        double actual = simpleStockManager.CalculateGeometricMeanOfStockPricesInGBCE();
        Assert.assertEquals(expected, actual, 0.01);
    }
}
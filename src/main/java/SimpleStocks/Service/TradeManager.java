package SimpleStocks.Service;

import SimpleStocks.Model.IStock;
import SimpleStocks.Model.ITrade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by David on 03/11/2016.
 */
public class TradeManager implements ITradeManager {
    private Collection<ITrade> Trades;

    public TradeManager()
    {
        this(new ArrayList<ITrade>());
    }

    public TradeManager(Collection<ITrade> trades)
    {
        Trades = trades;
    }

    public void RecordTrade(ITrade trade) {
        Trades.add(trade);
    }

    public Collection<ITrade> GetTrades() {
        return Trades;
    }

    public double CalculateVWSP(IStock stock)
    {
        Collection<ITrade> tradesFromLastFifteenMinutes = GetTradesInFifteenMinuteWindow(new Date());
        double totalTradedValue = 0;
        int totalTradedQuantity = 0;

        for(ITrade trade:tradesFromLastFifteenMinutes) {
            if (trade.GetTradedStock().GetStockSymbol().equals(stock.GetStockSymbol())) {
                totalTradedValue += (trade.GetTradedPrice() * trade.GetTradedQuantity());
                totalTradedQuantity += trade.GetTradedQuantity();
            }
        }
        return totalTradedValue/totalTradedQuantity;
    }

    public Collection<ITrade> GetTradesInFifteenMinuteWindow(Date endTime)
    {
        final int fifteenMinutesInMilliseconds = 900000;
        Collection<ITrade> tradesInFifteenMinuteWindow = new ArrayList<ITrade>();
        for(ITrade trade:Trades)
        {
            Date tradeTimestamp = trade.GetTradeTimestamp();
            Date EndTime = endTime;
            long timeSpanInMilliSeconds = (EndTime.getTime() - tradeTimestamp.getTime());
            if (timeSpanInMilliSeconds <= fifteenMinutesInMilliseconds)
            {
                tradesInFifteenMinuteWindow.add(trade);
            }
        }
        return tradesInFifteenMinuteWindow;
    }

    public double CalculateGeometricMeanOfStockPricesInGBCE() {
        double productOfSharePrice = 1;
        for(ITrade trade:Trades)
            productOfSharePrice *= trade.GetTradedPrice();
        return Math.pow(productOfSharePrice, (1.0/Trades.size()));
    }
}

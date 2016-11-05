package SimpleStocks.Model;

import SimpleStocks.Exceptions.TradeIndicatorNotFound;

import java.util.Date;

/**
 * Created by David on 03/11/2016.
 */
public class Trade implements ITrade {
    private Date TradeTimestamp;
    private IStock TradedStock;
    private int TradedQuantity;
    private TradeIndicator TradeIndicator;
    private double TradedPrice;

    public Trade(IStock stock, int quantityOfShares, TradeIndicator indicator, double tradePrice) throws TradeIndicatorNotFound {
        this(stock, quantityOfShares, indicator, tradePrice, new Date());
    }

    public Trade(IStock stock, int quantityOfShares, TradeIndicator indicator, double tradePrice, Date dateTime) throws TradeIndicatorNotFound {
        TradedStock = stock;
        TradedQuantity = quantityOfShares;
        TradeIndicator = CheckValidTradeIndicator(indicator);
        TradedPrice = tradePrice;
        TradeTimestamp = dateTime;
    }

    public IStock GetTradedStock() {
        return TradedStock;
    }

    public Date GetTradeTimestamp() {
        return TradeTimestamp;
    }

    public int GetTradedQuantity() {
        return TradedQuantity;
    }

    public TradeIndicator GetTradeIndicator() {
        return TradeIndicator;
    }

    public double GetTradedPrice() {
        return TradedPrice;
    }

    private TradeIndicator CheckValidTradeIndicator(TradeIndicator tradeIndicator) throws TradeIndicatorNotFound
    {
        if (tradeIndicator != TradeIndicator.BUY && tradeIndicator != TradeIndicator.SELL)
        {
            throw new TradeIndicatorNotFound("Trade Indicator must be either BUY or SELL");
        }
        return tradeIndicator;
    }

    @Override
    public String toString() {
        return "Trade:" +
                "\n    Timestamp: " + GetTradeTimestamp() +
                "\n    Stock : " + GetTradedStock().GetStockSymbol() +
                "\n    Quantity: " + GetTradedQuantity() +
                "\n    Trade Indicator: " + GetTradeIndicator().toString() +
                "\n    Trade Price: " + GetTradedPrice();
    }
}

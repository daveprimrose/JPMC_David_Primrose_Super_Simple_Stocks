package SimpleStocks;

import SimpleStocks.Exceptions.*;
import SimpleStocks.Model.*;
import SimpleStocks.Service.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

/**
 * Created by David on 03/11/2016.
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println("=======================");
        System.out.println("Simple Stocks Demo");
        System.out.println("======================= \n");

        DividendYieldDemo();
        PERatioDemo();
        RecordTradeDemo();
        CalculateVWSPBasedOnTradesInPastFifteenMinutesDemo();
        CalculateGeometricMeanOfStockPricesInGBCEDemo();

        System.out.println("=======================");
        System.out.println("End of Demo");
        System.out.println("=======================");
    }

    private static void DividendYieldDemo()
    {
        System.out.println("=======================");
        System.out.println("Calculate Dividend Yield for given stock with given market price \n");
        IStockManager stockManager = SetupDemoStockManager();
        final double marketPrice = 10.00;
        for (IStock stock:stockManager. GetStocks())
        {
            try {
                double dividendYield = stock.CalculateDividendYield(marketPrice);
                System.out.println("Dividend Yield for " + stock.GetStockSymbol() + " with market price " + marketPrice +  " is: " + dividendYield);
            }
            catch (StockTypeNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("=======================\n");
    }

    private static void PERatioDemo()
    {
        System.out.println("=======================");
        System.out.println("Calculate P/E Ratio for given stock with given market price \n");
        IStockManager stockManager = SetupDemoStockManager();
        final double marketPrice = 100.00;
        for (IStock stock:stockManager.GetStocks())
        {
            try {
                double peRatio= stock.CalculatePERatio(marketPrice);
                System.out.println("PE Ratio for " + stock.GetStockSymbol() + " with market price " + marketPrice +  " is: " + peRatio);
            }
            catch (StockTypeNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("======================= \n");
    }

    private static void RecordTradeDemo()
    {
        System.out.println("=======================");
        System.out.println("Record a trade, with timestamp, quantity of shares, buy or sell indicator and trade price \n");
        IStockManager stockManager = SetupDemoStockManager();
        ITradeManager tradeManager = new TradeManager();
        double marketPrice = 100;
        try {
            IStock teaStock = stockManager.GetStockByStockSymbol("TEA");
            int teaStockQuantity = 100;
            ITrade buyTeaTrade = new Trade(teaStock, teaStockQuantity, TradeIndicator.BUY, marketPrice);
            tradeManager.RecordTrade(buyTeaTrade);
            for (ITrade trade:tradeManager.GetTrades())
            {
                System.out.println(trade.toString());
            }
        }
        catch (Exception e)
        {
            System.out.println("Stock not found");
        }
        System.out.println("======================= \n");
    }

    private static void CalculateVWSPBasedOnTradesInPastFifteenMinutesDemo() {
        System.out.println("=======================");
        System.out.println("Calculate Volume Weighted Stock Price based on trades in past 15 minutes");
        IStockManager stockManager = SetupDemoStockManager();
        ITradeManager tradeManager = SetupDemoTradeManager();
        try
        {
            IStock ginStock = stockManager.GetStockByStockSymbol("GIN");
            double ginVWSA = tradeManager.CalculateVWSP(ginStock);
            for (ITrade trade:tradeManager.GetTrades())
                if(trade.GetTradedStock().GetStockSymbol() == ginStock.GetStockSymbol())
                    System.out.println(trade.toString() + "\n");
            System.out.println("Volume Weighted Stock Price for " + ginStock.GetStockSymbol() + " is: " + ginVWSA);
        }
        catch (Exception e)
        {
            System.out.println("Stock not found");
        }
        System.out.println("=======================\n");
    }

    private static void CalculateGeometricMeanOfStockPricesInGBCEDemo()
    {
        System.out.println("=======================");
        System.out.println("Calculate the GBCE All Share Index using the geometric mean of prices for all stocks");
        IStockManager stockManager = SetupDemoStockManager();
        ITradeManager tradeManager = SetupDemoTradeManager();
        for (ITrade trade:tradeManager.GetTrades())
        {
            System.out.println(trade.toString());
        }
        double geometricMean = tradeManager.CalculateGeometricMeanOfStockPricesInGBCE();
        System.out.println("Geometric mean of prices in GBCE is : " + geometricMean);
        System.out.println("=======================\n");
    }

    private static IStockManager SetupDemoStockManager()
    {
        Collection<IStock> stocks = new ArrayList<IStock>();
        try {
            stocks.add(new Stock("TEA", StockType.COMMON, 0, 100));
            stocks.add(new Stock("POP", StockType.COMMON, 8, 100));
            stocks.add(new Stock("ALE", StockType.COMMON, 23, 60));
            stocks.add(new Stock("GIN", StockType.PREFERRED, 8, 100, 0.02));
            stocks.add(new Stock("JOE", StockType.COMMON, 13, 250));
        }
        catch (StockTypeNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        return new StockManager(stocks);
    }

    private static ITradeManager SetupDemoTradeManager()
    {
        IStockManager stockManager = SetupDemoStockManager();
        ITradeManager tradeManager = new TradeManager();
        Random rnd = new Random();
        int numberOfTradesForEachStock = 3;
        System.out.println("\nCreating " + numberOfTradesForEachStock + " trades for each stock\n");
        for (IStock stock:stockManager.GetStocks()) {
            for (int i = 0; i < numberOfTradesForEachStock; i++) {
                int randomQuantity = rnd.nextInt(100) + 1;
                double randomPrice = (double) rnd.nextInt(100) + 1;
                try {
                    ITrade trade = new Trade(stock, randomQuantity, TradeIndicator.BUY, randomPrice);
                    tradeManager.RecordTrade(trade);
                } catch (TradeIndicatorNotFound e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        try {
            IStock ginStock = stockManager.GetStockByStockSymbol("GIN");
            int sixteenMinutesInMillis = 16*60*1000;
            ITrade ginTradeOutsideFifteenMinuteWindow = new Trade(ginStock, 1, TradeIndicator.BUY, 1, new Date(System.currentTimeMillis() - sixteenMinutesInMillis));
            tradeManager.RecordTrade(ginTradeOutsideFifteenMinuteWindow);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return tradeManager;
    }
}

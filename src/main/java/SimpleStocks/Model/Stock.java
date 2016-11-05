package SimpleStocks.Model;

import SimpleStocks.Exceptions.StockTypeNotFoundException;

/**
 * Created by David on 03/11/2016.
 */
public class Stock implements IStock {
    private String StockSymbol;
    private StockType StockType;
    private int VariableDividend;
    private double FixedDividend; //// TODO: 03/11/2016 would a fixed dividend ever change?
    private final int ParValue;

    public Stock(String stockSymbol, StockType stockType, int variableDividend, int parValue) throws StockTypeNotFoundException
    {
        this(stockSymbol, stockType, variableDividend, parValue, 0.0);
    }

    public Stock(String stockSymbol, StockType stockType, int variableDividend, int parValue, double fixedDividend) throws StockTypeNotFoundException
    {
        StockSymbol = stockSymbol;
        StockType = CheckValidStockType(stockType);
        StockType = stockType;
        VariableDividend = variableDividend;
        FixedDividend = fixedDividend;
        ParValue = parValue;
    }

    public String GetStockSymbol() {
        return StockSymbol;
    }

    public StockType GetStockType() {
        return StockType;
    }

    public int GetVariableDividend() {
        return VariableDividend;
    }

    public void SetVariableDividend(int variableDividend) {
        VariableDividend = variableDividend;
    }

    public double GetFixedDividend() {
        return FixedDividend;
    }

    public void SetFixedDividend(int fixedDividend) {
        FixedDividend = fixedDividend;
    }

    public int GetParValue() {
        return ParValue;
    }

    public int GetDividendForCommonStockType()
    {
        return VariableDividend; //assuming that the dividends will be paid at the same rate
    }

    public double GetDividendForPreferredStockType()
    {
        //assuming that the dividend of the preferred stock includes a fixed percentage of the PAR value
        return VariableDividend + (FixedDividend* ParValue);
    }

    private StockType CheckValidStockType(StockType stockType) throws StockTypeNotFoundException
    {
        if (stockType != StockType.COMMON && stockType != stockType.PREFERRED)
        {
            throw new StockTypeNotFoundException("StockType must be either COMMON or PREFERRED");
        }
        return stockType;
    }

    private double GetDividend()
    {
        double dividend;
        switch(StockType)
        {
            case COMMON:
                dividend = GetDividendForCommonStockType();
                break;
            default:
                dividend = GetDividendForPreferredStockType();
                break;
        }
        return dividend;
    }

    public double CalculateDividendYield(double marketPrice) throws StockTypeNotFoundException {
        double dividend = GetDividend();
        if (marketPrice == 0)
            System.out.println("Marketprice for Stock " + StockSymbol + " is 0.");
        double dividendYield =  dividend / marketPrice;
        return dividendYield;
    }

    public double CalculatePERatio(double marketPrice) throws StockTypeNotFoundException {
        double dividend = GetDividend();
        if(dividend == 0)
            System.out.println("Dividend for Stock " + StockSymbol + " is 0.");
        double peRatio = marketPrice / dividend;
        return peRatio;
    }
}

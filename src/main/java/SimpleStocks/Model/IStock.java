package SimpleStocks.Model;

import SimpleStocks.Exceptions.StockTypeNotFoundException;

/**
 * Created by David on 03/11/2016.
 */
public interface IStock {
    String GetStockSymbol();
    StockType GetStockType();
    int GetVariableDividend();
    void SetVariableDividend(int variableDividend);
    double GetFixedDividend();
    void SetFixedDividend(int fixedDividend);
    int GetParValue();
    double CalculateDividendYield(double marketPrice) throws StockTypeNotFoundException;
    double CalculatePERatio(double marketPrice) throws StockTypeNotFoundException;
}

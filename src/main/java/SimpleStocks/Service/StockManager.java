package SimpleStocks.Service;

import SimpleStocks.Model.IStock;
import SimpleStocks.Model.ITrade;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by David on 03/11/2016.
 */
public class StockManager implements IStockManager {
    private Collection<IStock> Stocks;

    public StockManager(){
        this(new ArrayList<IStock>());
    }

    public StockManager(Collection<IStock> stocks){
        Stocks = stocks;
    }

    public void AddStock(IStock stock) {
        Stocks.add(stock);
    }

    public Collection<IStock> GetStocks() {
        return Stocks;
    }

    public IStock GetStockByStockSymbol(String StockSymbol) throws Exception
    {
        IStock returnedStock = null;
        for (IStock stock:Stocks)
        {
            if(StockSymbol.equals(stock.GetStockSymbol()))
                returnedStock = stock;
        }
        if (returnedStock == null)
            throw new Exception("Stock Symbol not found");
        return returnedStock;
    }
}

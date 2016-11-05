package SimpleStocks.Service;

import SimpleStocks.Model.IStock;

import java.util.Collection;


/**
 * Created by David on 03/11/2016.
 */
public interface IStockManager {
    void AddStock(IStock stock);
    Collection<IStock> GetStocks();
    IStock GetStockByStockSymbol(String StockSymbol) throws Exception;
}

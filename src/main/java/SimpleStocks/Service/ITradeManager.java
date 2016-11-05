package SimpleStocks.Service;

import SimpleStocks.Model.IStock;
import SimpleStocks.Model.ITrade;
import SimpleStocks.Model.Trade;

import java.util.Collection;

/**
 * Created by David on 03/11/2016.
 */
public interface ITradeManager {
    void RecordTrade(ITrade trade);
    Collection<ITrade> GetTrades();
    double CalculateVWSP(IStock stock);
    double CalculateGeometricMeanOfStockPricesInGBCE();
}

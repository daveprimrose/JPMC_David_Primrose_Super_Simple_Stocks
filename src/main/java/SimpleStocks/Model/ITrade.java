package SimpleStocks.Model;

import java.util.Date;

/**
 * Created by David on 03/11/2016.
 */
public interface ITrade {
    IStock GetTradedStock();
    Date GetTradeTimestamp();
    int GetTradedQuantity();
    TradeIndicator GetTradeIndicator();
    double GetTradedPrice();
}

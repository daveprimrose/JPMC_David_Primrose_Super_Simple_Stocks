package SimpleStocks.Exceptions;

/**
 * Created by David on 05/11/2016.
 */
public class TradeIndicatorNotFound extends Exception {
    public TradeIndicatorNotFound() {
    }

    public TradeIndicatorNotFound(String message) {
        super(message);
    }

    public TradeIndicatorNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public TradeIndicatorNotFound(Throwable cause) {
        super(cause);
    }

    public TradeIndicatorNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

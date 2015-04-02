package calculation;

import com.google.inject.Inject;
import data.Timeseries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ai on 3/21/15.
 */
public class Calculator {
    private final static Logger log = LoggerFactory.getLogger(Calculator.class);

    private SimpleMovingAverage simpleMovingAverage;
    private MoneyFlowIndex moneyFlowIndex;

    @Inject
    public void setSimpleMovingAverage(final SimpleMovingAverage simpleMovingAverage) {
        this.simpleMovingAverage = simpleMovingAverage;
    }

    public double[] simpleMovingAverage(final Timeseries data, final int period) {
        return simpleMovingAverage.calculate(data.getClose(), period);
    }

    public double[] simpleMovingAverage(final double[] close, final int period) {
        return simpleMovingAverage.calculate(close, period);
    }

    @Inject
    public void setMoneyFlowIndex(final MoneyFlowIndex moneyFlowIndex) {
        this.moneyFlowIndex = moneyFlowIndex;
    }

    public double[] moneyFlowIndex(final Timeseries data, final int period) {
        return moneyFlowIndex.calculate(data.getHigh(), data.getLow(), data.getClose(), data.getVolume(), period);
    }

    public double[] moneyFlowIndex(final double[] high, final double[] low, final double[] close, final double[] volume, final int period) {
        return moneyFlowIndex.calculate(high, low, close, volume, period);
    }

}

package calculation;

import data.Timeseries;

/**
 * Created by ai on 3/21/15.
 */
public interface MoneyFlowIndex {
    public double[] calculate(final double[] high, final double[] low, final double[] close, final double[] volume, int period);
}

package calculation;

import data.Timeseries;

/**
 * Created by ai on 3/21/15.
 */
public interface SimpleMovingAverage {
    public double[] calculate(final double[] close, final int period);
}

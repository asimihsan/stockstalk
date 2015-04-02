package calculation;

import com.google.inject.Inject;

/**
 * Created by ai on 4/1/15.
 */
public class ExponentialMovingAverageImpl implements ExponentialMovingAverage {

    private SimpleMovingAverage simpleMovingAverage;

    @Inject
    public void setSimpleMovingAverage(final SimpleMovingAverage simpleMovingAverage) {
        this.simpleMovingAverage = simpleMovingAverage;
    }

    @Override
    public double[] calculate(double[] close, int period) {
        return simpleMovingAverage.calculate(close, period);
        /*
        final double[] output = new double[close.length];
        for (int i = output.length - 1; i > output.length - period; i--) {
            output[i] = Double.NaN;
        }
        for (int i = output.length - period; i >= 0; i--) {
            final int start = i, end = start + period - 1;
            for (int j = start; j <= end; j++) {
                output[i] += close[j] / period;
            }
        }
        return output;
        */
    }
}

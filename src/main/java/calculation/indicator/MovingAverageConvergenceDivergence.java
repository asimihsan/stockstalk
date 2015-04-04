package calculation.indicator;

import calculation.indicator.ExponentialMovingAverage;
import com.google.inject.Inject;

/**
 * Created by ai on 4/2/15.
 */
public class MovingAverageConvergenceDivergence {

    private final ExponentialMovingAverage exponentialMovingAverage;

    @Inject
    public MovingAverageConvergenceDivergence(final ExponentialMovingAverage exponentialMovingAverage) {
        this.exponentialMovingAverage = exponentialMovingAverage;
    }

    public double[] calculate(final double[] close, final int shortPeriod, final int longPeriod, final int signalPeriod) {
        final double[] shortEma = exponentialMovingAverage.calculate(close, shortPeriod);
        final double[] longEma = exponentialMovingAverage.calculate(close, longPeriod);
        final double[] difference = new double[close.length];
        for (int i = 0; i < difference.length; i++) {
            difference[i] = shortEma[i] - longEma[i];
        }
        final double[] signalEma = exponentialMovingAverage.calculate(difference, signalPeriod);
        final double[] histogram = new double[close.length];
        for (int i = 0; i < histogram.length; i++) {
            histogram[i] = difference[i] - signalEma[i];
        }
        return histogram;
    }
}

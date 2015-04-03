package calculation;

import com.google.inject.Inject;

/**
 * Created by ai on 4/1/15.
 */
public class ExponentialMovingAverageImpl implements ExponentialMovingAverage {

    @Override
    public double[] calculate(final double[] close, final int period) {
        final double[] output = new double[close.length];

        double startingValue = 0;
        for (int i = output.length - 1; i > output.length - period; i--) {
            output[i] = Double.NaN;
            startingValue += close[i] / period;
        }
        startingValue += close[output.length - period] / period;
        output[output.length - period] = startingValue;

        final double firstEmaConstant = 2.0d / (period + 1.0d);
        final double secondEmaConstant = 1 - firstEmaConstant;
        for (int i = output.length - period - 1; i >= 0; i--) {
            output[i] = close[i] * firstEmaConstant + output[i + 1] * secondEmaConstant;
        }
        return output;
    }
}

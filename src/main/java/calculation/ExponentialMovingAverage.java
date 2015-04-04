package calculation;

import calculation.util.CalculationUtil;
import com.google.inject.Inject;

/**
 * Created by ai on 4/1/15.
 */
public class ExponentialMovingAverage {

    private final CalculationUtil calculationUtil;

    @Inject
    public ExponentialMovingAverage(final CalculationUtil calculationUtil) {
        this.calculationUtil = calculationUtil;
    }

    public double[] calculate(final double[] close, final int period) {
        final double[] output = new double[close.length];

        double startingValue = 0;
        final int skipLength = calculationUtil.getCountToSkip(close);
        for (int i = output.length - 1; i > output.length - skipLength - 1; i--) {
            output[i] = Double.NaN;
        }
        for (int i = output.length - skipLength - 1; i > output.length - skipLength - period; i--) {
            output[i] = Double.NaN;
            startingValue += close[i] / period;
        }
        startingValue += close[output.length - skipLength - period] / period;
        output[output.length - skipLength - period] = startingValue;

        final double firstEmaConstant = 2.0d / (period + 1.0d);
        final double secondEmaConstant = 1 - firstEmaConstant;
        for (int i = output.length - skipLength - period - 1; i >= 0; i--) {
            output[i] = close[i] * firstEmaConstant + output[i + 1] * secondEmaConstant;
        }
        return output;
    }
}

package calculation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ai on 3/21/15.
 */
public class SimpleMovingAverage {
    private final static Logger log = LoggerFactory.getLogger(SimpleMovingAverage.class);

    public double[] calculate(final double[] close, final int period) {
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
    }

}

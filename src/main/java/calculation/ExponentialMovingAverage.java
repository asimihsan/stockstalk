package calculation;

/**
 * Created by ai on 4/1/15.
 */
public interface ExponentialMovingAverage {
    public double[] calculate(final double[] close, final int period);
}

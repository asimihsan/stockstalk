package calculation;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by ai on 4/2/15.
 */
public class ExponentialMovingAverageImplTest {
    private static final double[] close = new double[]{44.35, 43.93, 44.11, 44.27, 43.74, 44.04, 44.16, 44.08, 44.44, 44.34, 44.48, 45.01, 44.27, 43.4, 43.4, 43.66, 43.27, 42.42};
    private static final double DELTA = 1e-2;

    private ExponentialMovingAverage exponentialMovingAverage;

    @Before
    public void setup() {
        exponentialMovingAverage = new ExponentialMovingAverageImpl();
    }

    @Test
    public void testCalculate_Period12() {
        assertArrayEquals(
            new double[]{44.03, 43.97, 43.98, 43.96, 43.90, 43.93, 43.91, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN},
            exponentialMovingAverage.calculate(close, 12),
            DELTA);
    }
}

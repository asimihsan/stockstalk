package calculation.indicator;

import calculation.indicator.ExponentialMovingAverage;
import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppInjector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ai on 4/2/15.
 */
public class ExponentialMovingAverageTest {
    private static final double[] close = new double[]{44.35, 43.93, 44.11, 44.27, 43.74, 44.04, 44.16, 44.08, 44.44, 44.34, 44.48, 45.01, 44.27, 43.4, 43.4, 43.66, 43.27, 42.42};
    private static final double[] close2 = new double[]{10.0, 9.0, 8.0, 7.0};
    private static final double[] close3 = new double[]{10.0, 9.0, 8.0, 7.0, Double.NaN};
    private static final double[] close4 = new double[]{10.0, 9.0, 8.0, 7.0, Double.NaN, Double.NaN};
    private static final double DELTA = 1e-2;

    final Injector injector = Guice.createInjector(new AppInjector());
    private ExponentialMovingAverage exponentialMovingAverage;

    @Before
    public void setup() {
        exponentialMovingAverage = injector.getInstance(ExponentialMovingAverage.class);
    }

    @Test
    public void testCalculate_Period12() {
        assertArrayEquals(
                new double[]{44.03, 43.97, 43.98, 43.96, 43.90, 43.93, 43.91, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN},
                exponentialMovingAverage.calculate(close, 12),
                DELTA);
    }

    @Test
    public void testCalculate_Period2() {
        assertArrayEquals(
                new double[]{9.5, 8.5, 7.5, Double.NaN},
                exponentialMovingAverage.calculate(close2, 2),
                DELTA);
    }

    @Test
    public void testCalculate_Period2_Skip1() {
        assertArrayEquals(
                new double[]{9.5, 8.5, 7.5, Double.NaN, Double.NaN},
                exponentialMovingAverage.calculate(close3, 2),
                DELTA);
    }

    @Test
    public void testCalculate_Period2_Skip2() {
        assertArrayEquals(
                new double[]{9.5, 8.5, 7.5, Double.NaN, Double.NaN, Double.NaN},
                exponentialMovingAverage.calculate(close4, 2),
                DELTA);
    }

}

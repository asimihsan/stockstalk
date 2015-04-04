package calculation.indicator;

import calculation.indicator.MovingAverageConvergenceDivergence;
import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppInjector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ai on 4/3/15.
 */
public class MovingAverageConvergenceDivergenceTest {
    private static final double[] close = new double[]{441.35, 439.66, 442.93, 433.26, 434.58, 428.85, 443.86, 454.74, 452.97, 456.77, 463.84, 458.66, 460.71, 449.98, 445.52, 439.29, 442.78, 430.12, 417.2, 408.38, 405.46, 406.13, 398.67, 390.53, 392.05, 402.8, 426.24, 419.85, 429.8, 434.33, 435.69, 426.98, 426.21, 423.2, 427.72, 431.99, 429.79, 428.91, 442.66, 452.08, 461.14, 463.58, 461.91, 452.73, 452.08, 454.49, 455.72, 443.66, 432.5, 428.35, 428.43, 437.87, 431.72, 430.58, 425.66, 431.14, 420.05, 430.47, 441.4, 444.57, 448.97, 442.8, 450.81, 446.06, 448.85, 459.99};
    private static final double DELTA = 1e-2;

    final Injector injector = Guice.createInjector(new AppInjector());
    private MovingAverageConvergenceDivergence movingAverageConvergenceDivergence;

    @Before
    public void setup() {
        movingAverageConvergenceDivergence = injector.getInstance(MovingAverageConvergenceDivergence.class);
    }

    @Test
    public void testCalculate() throws Exception {
        final double[] macd = movingAverageConvergenceDivergence.calculate(close, 12, 26, 9);
        assertArrayEquals(
                new double[]{-1.20, -1.30, -1.25, -1.47, -0.77, 0.19, 2.22, 3.65, 4.46, 5.50, 6.27, 6.23, 6.18, 5.40, 4.83, 4.05, 3.19, 1.28, -0.47, -1.79, -2.73, -3.60, -4.67, -5.13, -4.54, -3.34, -2.19, -2.55, -2.25, -2.59, -3.39, -4.52, -5.11, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN},
                macd,
                DELTA
        );
    }
}

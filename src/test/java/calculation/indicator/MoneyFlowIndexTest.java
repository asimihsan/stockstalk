package calculation.indicator;

import calculation.indicator.MoneyFlowIndex;
import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppInjector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by ai on 4/1/15.
 */
public class MoneyFlowIndexTest {
    private static final double DELTA = 1e-2;
    private static final double[] high = new double[]{25.16, 24.56, 24.48, 24.60, 24.27, 24.27, 24.55, 24.69, 25.12, 25.31, 25.01, 25.04, 25.26, 25.32, 25.45, 25.67, 25.72, 25.74, 25.60, 25.54, 25.39, 25.28, 25.13, 25.29, 25.34, 25.68, 25.58, 25.16, 24.76, 24.83};
    private static final double[] low = new double[]{24.27, 23.43, 24.24, 24.20, 23.72, 23.78, 23.89, 24.27, 24.34, 25.03, 24.71, 24.83, 24.91, 24.92, 25.17, 25.29, 25.46, 25.54, 25.06, 25.05, 25.03, 24.93, 24.75, 24.85, 25.06, 24.81, 24.95, 24.78, 24.60, 24.32};
    private static final double[] close = new double[]{25.00, 24.44, 24.33, 24.28, 24.20, 23.88, 24.02, 24.57, 24.45, 25.05, 24.97, 24.88, 24.92, 25.04, 25.37, 25.41, 25.56, 25.56, 25.45, 25.06, 25.34, 25.05, 25.00, 24.89, 25.11, 25.07, 25.55, 25.04, 24.71, 24.75};
    private static final double[] volume = new double[]{13379374, 11356180, 7168965, 8870318, 9690604, 9256870, 13294865, 12366132, 11798009, 7457091, 5023469, 5624742, 5672914, 7164726, 5818162, 7395274, 5799259, 10906659, 12986669, 22572712, 9773569, 16018729, 16568487, 16067044, 15918948, 22964080, 18357606, 24691414, 12271740, 18730144};

    final Injector injector = Guice.createInjector(new AppInjector());
    private MoneyFlowIndex moneyFlowIndex;

    @Before
    public void init() {
        moneyFlowIndex = injector.getInstance(MoneyFlowIndex.class);
    }

    @Test
    public void testCalculate_Period14() {
        assertArrayEquals(
            new double[]{30.84, 21.53, 22.18, 22.38, 24.07, 26.51, 23.76, 31.83, 42.80, 41.30, 33.87, 31.53, 28.41, 36.27, 45.11, 49.47, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN},
            moneyFlowIndex.calculate(high, low, close, volume, 14),
            DELTA);
    }
}

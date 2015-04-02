package calculation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ai on 4/1/15.
 */
public class SimpleMovingAverageImplTest {
    private static final double DELTA = 1e-2;

    private SimpleMovingAverage simpleMovingAverage;

    @Before
    public void setup() {
        simpleMovingAverage = new SimpleMovingAverageImpl();
    }

    @Test
    public void testCalculate_Period3() throws Exception {
        final double[] data = new double[]{17, 17, 17, 18, 17, 10, 17, 17, 16};
        assertArrayEquals(
                new double[]{17.2, 15.8, 15.8, 15.8, 15.4, Double.NaN, Double.NaN, Double.NaN, Double.NaN},
                simpleMovingAverage.calculate(data, 5),
                DELTA);
    }

    @Test
    public void testCalculate_Period5() throws Exception {
        final double[] data = new double[]{17, 17, 17, 18, 17, 10, 17, 17, 16};
        assertArrayEquals(
                new double[]{17.0, 17.33333333, 17.33333333, 15.0, 14.66666666, 14.66666666, 16.66666666,Double.NaN, Double.NaN},
                simpleMovingAverage.calculate(data, 3),
                DELTA);
    }
}

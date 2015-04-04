package calculation.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ai on 4/3/15.
 */
public class CalculationUtilTest {
    private final double[] test = new double[]{ 5.0d, 4.0, 3.0d, 2.0d, 1.0d };
    private final double[] test2 = new double[]{ 5.0d, 4.0, 3.0d, Double.NaN, Double.NaN };
    private final double[] test3 = new double[]{ Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN };
    private final double[] test_empty = new double[]{ };

    private CalculationUtil calculationUtil;

    @Before
    public void setUp() {
        calculationUtil = new CalculationUtil();
    }

    @Test
    public void testGetCountToSkip_1() throws Exception {
        assertEquals(0, calculationUtil.getCountToSkip(test));
    }

    @Test
    public void testGetCountToSkip_2() throws Exception {
        assertEquals(2, calculationUtil.getCountToSkip(test2));
    }

    @Test
    public void testGetCountToSkip_3() throws Exception {
        assertEquals(5, calculationUtil.getCountToSkip(test3));
    }

    @Test
    public void testGetCountToSkip_Empty() throws Exception {
        assertEquals(0, calculationUtil.getCountToSkip(test_empty));
    }

}

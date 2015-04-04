package calculation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by ai on 4/3/15.
 */
public class CalculationUtil {
    private static final Logger log = LoggerFactory.getLogger(CalculationUtil.class);

    /**
     * For an array of data return how many of the leading entries (right to left) are NaN. Calculations cannot
     * do anything with these leading NaN value so will have to skip them.
     *
     * @param data
     * @return
     */
    public int getCountToSkip(final double[] data) {
        int count = 0;
        for (int i = data.length - 1; i >= 0; i--, count++) {
            if (!Double.isNaN(data[i])) {
                break;
            }
        }
        return count;

    }
}

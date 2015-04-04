package calculation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ai on 3/21/15.
 */
public class MoneyFlowIndex {
    private final static Logger log = LoggerFactory.getLogger(MoneyFlowIndex.class);

    public double[] calculate(final double[] high, final double[] low, final double[] close, final double[] volume, int period) {
        final double[] output = new double[close.length];
        final double[] rawMoneyFlow = new double[close.length];
        final double[] typicalPrice = new double[close.length];
        for (int i = output.length - 1; i > output.length - period - 1; i--) {
            output[i] = Double.NaN;
        }
        for (int i = output.length - 1; i >= 0; i--) {
            final double typicalPriceValue = ((high[i] + low[i] + close[i]) / 3);
            typicalPrice[i] = typicalPriceValue;
            rawMoneyFlow[i] = typicalPriceValue * volume[i];
        }
        for (int i = output.length - period - 1; i >= 0; i--) {
            double positiveMoneyFlow = 0, negativeMoneyFlow = 0;
            final int start = i, end = start + period - 1;
            for (int j = start; j <= end; j++) {
                if (typicalPrice[j] >= typicalPrice[j + 1]) {
                    positiveMoneyFlow += rawMoneyFlow[j];
                } else {
                    negativeMoneyFlow += rawMoneyFlow[j];
                }
            }
            final double moneyFlowRatio = positiveMoneyFlow / negativeMoneyFlow;
            output[i] = 100.0d - 100.0d / (1.0d + moneyFlowRatio);
        }
        return output;
    }

}

import calculation.Calculator;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import config.AppInjector;
import data.DataSource;
import data.HistoricalData;
import data.Timeseries;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by ai on 3/19/15.
 */

public class Main {
    private final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        log.info("main() entry.");
        Injector injector = Guice.createInjector(new AppInjector());

        final String ticker = "^GSPC";
        final DateTime end = DateTime.now().minusDays(1);
        final DateTime start = end.minusYears(1);
        HistoricalData data = new HistoricalData(DataSource.YAHOO_FINANCE, ticker, start, end);
        Timeseries tsd = new Timeseries(data);

        Calculator calculator = injector.getInstance(Calculator.class);
        final double[] ema = calculator.exponentialMovingAverage(tsd, 50);
        log.info(Arrays.toString(ema));
    }
}

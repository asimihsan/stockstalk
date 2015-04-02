package data;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ai on 3/19/15.
 */
public class HistoricalData {
    private final static Logger log = LoggerFactory.getLogger(HistoricalData.class);
    private final static String URL = "http://real-chart.finance.yahoo.com/table.csv?s=%s&d=%s&e=%s&f=%s&g=d&a=%s&b=%s&c=%s&ignore=.csv";
    private final static String FILENAME = "%s_%s-%s-%s_%s-%s-%s.csv";
    private final File outputFile;
    private final DataSource dataSource;

    public HistoricalData(final DataSource dataSource, final String ticker, final DateTime start, final DateTime end) throws IOException {
        outputFile = download(dataSource, ticker, start, end);
        this.dataSource = dataSource;
    }

    private File download(final DataSource dataSource, final String ticker, final DateTime start, final DateTime end) throws IOException {
        log.info("download() entry. dataSource: {}, ticker: {}, start: {}, end: {}", dataSource, ticker, start, end);
        final String filename = String.format(FILENAME, ticker, start.getYear(), start.getMonthOfYear(),
                start.getDayOfMonth(), end.getYear(), end.getMonthOfYear(), end.getDayOfMonth());
        final File output = new File(String.format("/Users/ai/stockdata/%s", filename));
        if (!output.exists()) {
            log.info("data file does not already exist, downloading...");
            switch(dataSource) {
                case YAHOO_FINANCE:
                    final String url = String.format(URL, ticker, end.getMonthOfYear(), end.getDayOfMonth(),
                            end.getYear(), start.getMonthOfYear(), start.getDayOfMonth(), start.getYear());
                    log.info("url: {}", url);
                    FileUtils.copyURLToFile(new URL(url), output);
                    log.info("download finished.");
                    break;
            }
        }
        log.info("download() exit.");
        return output;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}

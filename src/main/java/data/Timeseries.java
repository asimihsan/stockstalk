package data;

import com.google.common.primitives.Doubles;
import com.opencsv.CSVReader;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Created by ai on 3/20/15.
 */
public class Timeseries {
    private final static Logger log = LoggerFactory.getLogger(Timeseries.class);
    private DateTime[] date;
    private double[] open;
    private double[] high;
    private double[] low;
    private double[] close;
    private double[] volume;
    private double[] percentageChange;
    private final Map<String, double[]> calculations = new HashMap<>();

    public Timeseries(final HistoricalData historicalData) throws IOException {
        populateFromCSV(historicalData.getOutputFile().toPath(), historicalData.getDataSource());
        calculatePercentageChange();
    }

    private void calculatePercentageChange() {
        percentageChange = new double[close.length];
        percentageChange[percentageChange.length - 1] = Double.NaN;
        for (int i = percentageChange.length - 2; i >= 0; i--) {
            percentageChange[i] = (close[i] - close[i + 1]) / close[i + 1];
        }
    }

    private void populateFromCSV(final Path input, final DataSource dataSource) throws IOException {
        List<DateTime> dateTemp = new ArrayList<>();
        Map<String, List<Double>> csvData = new HashMap<>();
        csvData.put("open", new ArrayList<>());
        csvData.put("high", new ArrayList<>());
        csvData.put("low", new ArrayList<>());
        csvData.put("close", new ArrayList<>());
        csvData.put("volume", new ArrayList<>());

        try (
           final Reader r = Files.newBufferedReader(input);
           final CSVReader csvReader = new CSVReader(r);
        ) {
            String[] nextLine;
            csvReader.readNext();
            while ((nextLine = csvReader.readNext()) != null) {
                switch(dataSource) {
                    case YAHOO_FINANCE:
                        final String[] dateElems = nextLine[0].split("-");
                        final DateTime date = new DateTime(Integer.valueOf(dateElems[0]),
                                Integer.valueOf(dateElems[1]), Integer.valueOf(dateElems[2]), 0, 0);
                        dateTemp.add(date);
                        csvData.get("open").add(new Double(nextLine[1]));
                        csvData.get("high").add(new Double(nextLine[2]));
                        csvData.get("low").add(new Double(nextLine[3]));
                        csvData.get("close").add(new Double(nextLine[6]));
                        csvData.get("volume").add(new Double(nextLine[5]));
                        break;
                }
            }
        }
        date = (DateTime[])dateTemp.toArray(new DateTime[dateTemp.size()]);
        open = Doubles.toArray(csvData.get("open"));
        high = Doubles.toArray(csvData.get("high"));
        low = Doubles.toArray(csvData.get("low"));
        close = Doubles.toArray(csvData.get("close"));
        volume = Doubles.toArray(csvData.get("volume"));
    }

    public DateTime[] getDate() {
        return date;
    }

    public double[] getOpen() {
        return open;
    }

    public double[] getHigh() {
        return high;
    }

    public double[] getLow() {
        return low;
    }

    public double[] getClose() {
        return close;
    }

    public double[] getVolume() {
        return volume;
    }

    public double[] getPercentageChange() {
        return percentageChange;
    }

    public double[] getCalculation(final String calculation) {
        return calculations.get(calculation);
    }

    public void setCalculation(final String calculation, final double[] data) {
        calculations.put(calculation, data);
    }
}

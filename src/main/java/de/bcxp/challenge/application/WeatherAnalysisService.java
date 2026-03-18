package de.bcxp.challenge.application;

import de.bcxp.challenge.domain.model.WeatherRecord;
import de.bcxp.challenge.domain.service.TemperatureSpreadCalculator;
import de.bcxp.challenge.infrastructure.DataReader;

import java.util.List;

/**
 * Application service that orchestrates weather data analysis.
 */
public class WeatherAnalysisService {

    private final DataReader<WeatherRecord> reader;
    private final TemperatureSpreadCalculator calculator;

    public WeatherAnalysisService(DataReader<WeatherRecord> reader, TemperatureSpreadCalculator calculator) {
        this.reader = reader;
        this.calculator = calculator;
    }

    /**
     * Reads weather data from the given resource and finds the day with the smallest temperature spread.
     *
     * @param resourcePath the path to the weather data source
     * @return the day number as a String
     * @throws de.bcxp.challenge.infrastructure.DataReadException if the data source cannot be read
     */
    public String findDayWithSmallestTemperatureSpread(String resourcePath) {
        List<WeatherRecord> records = reader.read(resourcePath);
        return calculator.findDayWithSmallestSpread(records);
    }
}

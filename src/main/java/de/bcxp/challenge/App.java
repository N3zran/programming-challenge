package de.bcxp.challenge;

import de.bcxp.challenge.application.WeatherAnalysisService;
import de.bcxp.challenge.domain.model.WeatherRecord;
import de.bcxp.challenge.domain.service.TemperatureSpreadCalculator;
import de.bcxp.challenge.infrastructure.csv.CsvFileReader;
import de.bcxp.challenge.infrastructure.mapper.WeatherRowMapper;
import de.bcxp.challenge.infrastructure.DataReader;


public final class App {

    private static final String WEATHER_CSV = "/de/bcxp/challenge/weather.csv";

    /**
     * Main entry method of the program.
     *
     * @param args the CLI arguments passed
     */
    public static void main(String... args) throws Exception {

        // --- Weather analysis ---
        DataReader<WeatherRecord> weatherReader = new CsvFileReader<>(',', new WeatherRowMapper());
        WeatherAnalysisService weatherService = new WeatherAnalysisService(weatherReader, new TemperatureSpreadCalculator());

        String dayWithSmallestTempSpread = weatherService.findDayWithSmallestTemperatureSpread(WEATHER_CSV);
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

    }
}

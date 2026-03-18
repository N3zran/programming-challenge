package de.bcxp.challenge;

import de.bcxp.challenge.application.CountryAnalysisService;
import de.bcxp.challenge.application.WeatherAnalysisService;
import de.bcxp.challenge.domain.model.CountryRecord;
import de.bcxp.challenge.domain.model.WeatherRecord;
import de.bcxp.challenge.domain.service.PopulationDensityCalculator;
import de.bcxp.challenge.domain.service.TemperatureSpreadCalculator;
import de.bcxp.challenge.infrastructure.csv.CsvFileReader;
import de.bcxp.challenge.infrastructure.mapper.CountryRowMapper;
import de.bcxp.challenge.infrastructure.mapper.WeatherRowMapper;
import de.bcxp.challenge.infrastructure.DataReader;


public final class App {

    private static final String WEATHER_CSV = "/de/bcxp/challenge/weather.csv";
    private static final String COUNTRIES_CSV = "/de/bcxp/challenge/countries.csv";

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

        // --- Country analysis ---
        DataReader<CountryRecord> countryReader = new CsvFileReader<>(';', new CountryRowMapper());
        CountryAnalysisService countryService = new CountryAnalysisService(countryReader, new PopulationDensityCalculator());

        String countryWithHighestDensity = countryService.findCountryWithHighestPopulationDensity(COUNTRIES_CSV);
        System.out.printf("Country with highest population density: %s%n", countryWithHighestDensity);
    }
}

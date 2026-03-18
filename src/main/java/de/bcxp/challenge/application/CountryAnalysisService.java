package de.bcxp.challenge.application;

import de.bcxp.challenge.domain.model.CountryRecord;
import de.bcxp.challenge.domain.service.PopulationDensityCalculator;
import de.bcxp.challenge.infrastructure.DataReader;

import java.util.List;

/**
 * Application service that orchestrates country data analysis.
 */
public class CountryAnalysisService {

    private final DataReader<CountryRecord> reader;
    private final PopulationDensityCalculator calculator;

    public CountryAnalysisService(DataReader<CountryRecord> reader, PopulationDensityCalculator calculator) {
        this.reader = reader;
        this.calculator = calculator;
    }

    /**
     * Reads country data from the given resource and finds the country with the highest population density.
     *
     * @param resourcePath the path to the country data source
     * @return the country name
     * @throws de.bcxp.challenge.infrastructure.DataReadException if the data source cannot be read
     */
    public String findCountryWithHighestPopulationDensity(String resourcePath) {
        List<CountryRecord> records = reader.read(resourcePath);
        return calculator.findCountryWithHighestDensity(records);
    }
}

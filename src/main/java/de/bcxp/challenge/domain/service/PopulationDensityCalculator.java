package de.bcxp.challenge.domain.service;

import de.bcxp.challenge.domain.model.CountryRecord;

import java.util.List;

/**
 * Finds population density values for the given list of country.
 */
public class PopulationDensityCalculator {

    /**
     * Returns the name of the country with the highest population density.
     *
     * @param records the country records to analyse
     * @return the country name
     */
    public String findCountryWithHighestDensity(List<CountryRecord> records) {
        CountryRecord result = ExtremumSelector.findMax(records, CountryRecord::getPopulationDensity);
        return result.getName();
    }
}

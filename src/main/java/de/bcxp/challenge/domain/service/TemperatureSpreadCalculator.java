package de.bcxp.challenge.domain.service;

import de.bcxp.challenge.domain.model.WeatherRecord;

import java.util.List;

/**
 * Finds temperature spread of the given list of days.
 */
public class TemperatureSpreadCalculator {

    /**
     * Returns the day number with the smallest temperature spread.
     *
     * @param records the weather records to analyse
     * @return the day number as a String
     * @throws IllegalArgumentException if records is null or empty
     */
    public String findDayWithSmallestSpread(List<WeatherRecord> records) {
        WeatherRecord result = ExtremumSelector.findMin(records, WeatherRecord::getTemperatureSpread);
        return String.valueOf(result.getDay());
    }
}

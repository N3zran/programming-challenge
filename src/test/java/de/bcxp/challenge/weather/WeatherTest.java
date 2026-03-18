package de.bcxp.challenge.weather;

import de.bcxp.challenge.application.WeatherAnalysisService;
import de.bcxp.challenge.domain.model.WeatherRecord;
import de.bcxp.challenge.domain.service.TemperatureSpreadCalculator;
import de.bcxp.challenge.infrastructure.csv.CsvFileReader;
import de.bcxp.challenge.infrastructure.mapper.WeatherRowMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for weather analysis
 */
class WeatherTest {

    private final TemperatureSpreadCalculator calculator = new TemperatureSpreadCalculator();

    @Test
    void shouldFindDayWithSmallestSpread() {
        List<WeatherRecord> records = Arrays.asList(
                new WeatherRecord(1, 88, 59),  // spread = 29
                new WeatherRecord(14, 61, 59), // spread = 2 ← smallest
                new WeatherRecord(5, 90, 66)   // spread = 24
        );

        assertThat(calculator.findDayWithSmallestSpread(records)).isEqualTo("14");
    }

    @Test
    void shouldHandleSingleRecord() {
        List<WeatherRecord> records = Collections.singletonList(new WeatherRecord(7, 73, 57));

        assertThat(calculator.findDayWithSmallestSpread(records)).isEqualTo("7");
    }

    @Test
    void shouldHandleZeroSpread() {
        List<WeatherRecord> records = Arrays.asList(
                new WeatherRecord(1, 70, 70),  // spread = 0
                new WeatherRecord(2, 80, 60)   // spread = 20
        );

        assertThat(calculator.findDayWithSmallestSpread(records)).isEqualTo("1");
    }

    @Test
    void shouldFindDayWithSmallestTemperatureSpread() {
        WeatherAnalysisService service = new WeatherAnalysisService(
                new CsvFileReader<>(',', new WeatherRowMapper()),
                new TemperatureSpreadCalculator());

        String result = service.findDayWithSmallestTemperatureSpread("/de/bcxp/challenge/weather.csv");

        assertThat(result).isEqualTo("14");
    }
}

package de.bcxp.challenge.country;

import de.bcxp.challenge.application.CountryAnalysisService;
import de.bcxp.challenge.domain.model.CountryRecord;
import de.bcxp.challenge.domain.service.PopulationDensityCalculator;
import de.bcxp.challenge.infrastructure.csv.CsvFileReader;
import de.bcxp.challenge.infrastructure.mapper.CountryRowMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for country analysis.
 */
class CountryTest {

    private final PopulationDensityCalculator calculator = new PopulationDensityCalculator();

    @Test
    void shouldFindCountryWithHighestDensity() {
        List<CountryRecord> records = Arrays.asList(
                new CountryRecord("Germany", 83120520, 357386),
                new CountryRecord("Malta", 516100, 316),
                new CountryRecord("Finland", 5527493, 338424)
        );

        assertThat(calculator.findCountryWithHighestDensity(records)).isEqualTo("Malta");
    }

    @Test
    void shouldHandleSingleRecord() {
        List<CountryRecord> records = Collections.singletonList(
                new CountryRecord("Luxembourg", 633347, 2586)
        );

        assertThat(calculator.findCountryWithHighestDensity(records)).isEqualTo("Luxembourg");
    }

    @Test
    void shouldFindCountryWithHighestPopulationDensity() {
        CountryAnalysisService service = new CountryAnalysisService(
                new CsvFileReader<>(';', new CountryRowMapper()),
                new PopulationDensityCalculator());

        String result = service.findCountryWithHighestPopulationDensity("/de/bcxp/challenge/countries.csv");

        assertThat(result).isEqualTo("Malta");
    }
}

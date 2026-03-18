package de.bcxp.challenge.dataReader;

import de.bcxp.challenge.domain.model.CountryRecord;
import de.bcxp.challenge.domain.model.WeatherRecord;
import de.bcxp.challenge.infrastructure.csv.CsvFileReader;
import de.bcxp.challenge.infrastructure.mapper.CountryRowMapper;
import de.bcxp.challenge.infrastructure.mapper.WeatherRowMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the CSV file reading and row mapping.
 */
class InfrastructureTest {

    @Test
    void shouldReadWeatherCsv() throws Exception {
        CsvFileReader<WeatherRecord> reader = new CsvFileReader<>(',', new WeatherRowMapper());

        List<WeatherRecord> records = reader.read("/de/bcxp/challenge/weather.csv");

        assertThat(records).hasSize(30);
        assertThat(records.get(0).getDay()).isEqualTo(1);
        assertThat(records.get(0).getMaxTemperature()).isEqualTo(88.0);
    }

    @Nested
    class WeatherRowMapperTests {

        private final WeatherRowMapper mapper = new WeatherRowMapper();
        private final String[] header = {"Day", "MxT", "MnT", "AvT"};

        @Test
        void shouldMapValidRow() {
            WeatherRecord result = mapper.map(header, new String[]{"1", "88", "59", "74"});

            assertThat(result.getDay()).isEqualTo(1);
            assertThat(result.getMaxTemperature()).isEqualTo(88.0);
            assertThat(result.getMinTemperature()).isEqualTo(59.0);
            assertThat(result.getTemperatureSpread()).isEqualTo(29.0);
        }

        @Test
        void shouldHandleDecimalTemperatures() {
            WeatherRecord result = mapper.map(header, new String[]{"5", "90.5", "66.3", "78"});

            assertThat(result.getMaxTemperature()).isEqualTo(90.5);
            assertThat(result.getMinTemperature()).isEqualTo(66.3);
        }
    }

    @Nested
    class CountryRowMapperTests {

        private final CountryRowMapper mapper = new CountryRowMapper();
        private final String[] header = {"Name", "Capital", "Accession", "Population", "Area (km²)", "GDP (US$ M)"};

        @Test
        void shouldMapValidRow() {
            CountryRecord result = mapper.map(header, new String[]{"Austria", "Vienna", "1995", "8926000", "83855", "447718"});

            assertThat(result.getName()).isEqualTo("Austria");
            assertThat(result.getPopulation()).isEqualTo(8926000L);
            assertThat(result.getAreaInSquareKm()).isEqualTo(83855.0);
        }

        @Test
        void shouldHandleDifferentNumberFormat() {
            CountryRecord result = mapper.map(header, new String[]{"Croatia", "Zagreb", "2013", "4.036.355,00", "56594", "60702"});

            assertThat(result.getName()).isEqualTo("Croatia");
            assertThat(result.getPopulation()).isEqualTo(4036355L);
        }

        @Test
        void shouldCalculatePopulationDensity() {
            CountryRecord result = mapper.map(header, new String[]{"Malta", "Valletta", "2004", "516100", "316", "14859"});

            assertThat(result.getPopulationDensity()).isGreaterThan(1633.0).isLessThan(1634.0);
        }
    }
}

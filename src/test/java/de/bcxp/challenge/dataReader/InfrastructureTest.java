package de.bcxp.challenge.dataReader;

import de.bcxp.challenge.domain.model.WeatherRecord;
import de.bcxp.challenge.infrastructure.csv.CsvFileReader;
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

}

package de.bcxp.challenge.infrastructure.csv;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import de.bcxp.challenge.infrastructure.mapper.RowMapper;
import de.bcxp.challenge.infrastructure.DataReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads a CSV file from the classpath and maps each row to a domain object.
 * <p>
 * Configurable delimiter to support different CSV formats.
 * </p>
 *
 * @param <T> the domain type each row is mapped to
 */
public class CsvFileReader<T> implements DataReader<T> {

    private final char delimiter;
    private final RowMapper<T> mapper;

    public CsvFileReader(char delimiter, RowMapper<T> mapper) {
        this.delimiter = delimiter;
        this.mapper = mapper;
    }

    @Override
    public List<T> read(String resourcePath) throws IOException, CsvException {
        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath);
             CSVReader csvReader = buildReader(inputStream)) {

            List<String[]> allRows = csvReader.readAll();
            String[] header = allRows.get(0);

            List<T> records = new ArrayList<>();
            for (int i = 1; i < allRows.size(); i++) {
                records.add(mapper.map(header, allRows.get(i)));
            }
            return records;
        }
    }

    private CSVReader buildReader(InputStream inputStream) {
        return new CSVReaderBuilder(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .withCSVParser(new CSVParserBuilder().withSeparator(delimiter).build())
                .build();
    }
}

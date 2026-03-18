package de.bcxp.challenge.infrastructure.mapper;

import de.bcxp.challenge.domain.model.CountryRecord;

/**
 * Maps a raw CSV row into a {@link CountryRecord}.
 * <p>
 * Expects columns: Name, Population, Area (km²).
 * </p>
 */
public class CountryRowMapper implements RowMapper<CountryRecord> {

    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_POPULATION = "Population";
    private static final String COLUMN_AREA = "Area (km²)";

    @Override
    public CountryRecord map(String[] header, String[] row) {
        int nameIndex = findColumnIndex(header, COLUMN_NAME);
        int populationIndex = findColumnIndex(header, COLUMN_POPULATION);
        int areaIndex = findColumnIndex(header, COLUMN_AREA);

        String name = row[nameIndex].trim();
        long population = parsePopulation(row[populationIndex].trim());
        double area = Double.parseDouble(row[areaIndex].trim());

        return new CountryRecord(name, population, area);
    }

    private int findColumnIndex(String[] header, String columnName) {
        for (int i = 0; i < header.length; i++) {
            if (header[i].trim().equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Parses population values that may use different delimiter for number formatting (dot or comma).
     */
    private long parsePopulation(String value) {
        String normalized = value.replace(".", "").replace(",", ".");
        return (long) Double.parseDouble(normalized);
    }
}

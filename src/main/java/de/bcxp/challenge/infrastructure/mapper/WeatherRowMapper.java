package de.bcxp.challenge.infrastructure.mapper;

import de.bcxp.challenge.domain.model.WeatherRecord;

/**
 * Maps a raw CSV row into a {@link WeatherRecord}.
 * <p>
 * Expects columns: Day, MxT (max temperature), MnT (min temperature).
 * </p>
 */
public class WeatherRowMapper implements RowMapper<WeatherRecord> {

    private static final String COLUMN_DAY = "Day";
    private static final String COLUMN_MAX_TEMP = "MxT";
    private static final String COLUMN_MIN_TEMP = "MnT";

    @Override
    public WeatherRecord map(String[] header, String[] row) {
        int dayIndex = findColumnIndex(header, COLUMN_DAY);
        int maxTempIndex = findColumnIndex(header, COLUMN_MAX_TEMP);
        int minTempIndex = findColumnIndex(header, COLUMN_MIN_TEMP);

        int day = Integer.parseInt(row[dayIndex].trim());
        double maxTemp = Double.parseDouble(row[maxTempIndex].trim());
        double minTemp = Double.parseDouble(row[minTempIndex].trim());

        return new WeatherRecord(day, maxTemp, minTemp);
    }

    private int findColumnIndex(String[] header, String columnName) {
        for (int i = 0; i < header.length; i++) {
            if (header[i].trim().equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        return -1;
    }
}

package de.bcxp.challenge.infrastructure.mapper;

/**
 * Maps a raw data row into a typed domain object.
 *
 * @param <T> the target domain type
 */
public interface RowMapper<T> {

    /**
     * Maps a single raw row into a domain object.
     *
     * @param header the column headers from the data source
     * @param row    the raw string values for a single record
     * @return the mapped domain object
     */
    T map(String[] header, String[] row);
}

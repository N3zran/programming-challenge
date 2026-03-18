package de.bcxp.challenge.infrastructure;

import java.util.List;

/**
 * Interface for reading data from a source and returning typed domain objects.
 *
 * @param <T> the type of records returned after reading
 */
public interface DataReader<T> {

    /**
     * Reads data from the configured source and returns a list of parsed records.
     *
     * @param resourcePath the path or identifier of the data source
     * @return a list of parsed records, never null
     * @throws DataReadException if the source cannot be read or parsed
     */
    List<T> read(String resourcePath);
}

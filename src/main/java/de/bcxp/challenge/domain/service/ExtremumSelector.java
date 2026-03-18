package de.bcxp.challenge.domain.service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.ToDoubleFunction;

/**
 * Generic utility for finding the element with the minimum or maximum value
 * of a given metric in a list.
 */
public class ExtremumSelector {

    /**
     * Finds the element with the minimum value of the given metric.
     *
     * @param records the list to search
     * @param metric  the function extracting the comparable value
     * @param <T>     the element type
     * @return the element with the minimum metric value
     * @throws IllegalArgumentException if the list is null or empty
     */
    public static <T> T findMin(List<T> records, ToDoubleFunction<T> metric) {
        validateRecords(records);
        return records.stream()
                .min(Comparator.comparingDouble(metric))
                .orElseThrow(() -> new NoSuchElementException("No minimum found in empty stream"));
    }

    /**
     * Finds the element with the maximum value of the given metric.
     *
     * @param records the list to search
     * @param metric  the function extracting the comparable value
     * @param <T>     the element type
     * @return the element with the maximum metric value
     * @throws IllegalArgumentException if the list is null or empty
     */
    public static <T> T findMax(List<T> records, ToDoubleFunction<T> metric) {
        validateRecords(records);
        return records.stream()
                .max(Comparator.comparingDouble(metric))
                .orElseThrow(() -> new NoSuchElementException("No maximum found in empty stream"));
    }

    private static <T> void validateRecords(List<T> records) {
        if (records == null || records.isEmpty()) {
            throw new IllegalArgumentException("Records list must not be null or empty");
        }
    }
}

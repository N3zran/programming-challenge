package de.bcxp.challenge.domain.service;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;

/**
 * Generic utility for finding the element with the minimum or maximum value
 * of a given metric in a list.
 */
public class ExtremumSelector {

    public static <T> T findMin(List<T> records, ToDoubleFunction<T> metric) {
        return records.stream()
                .min(Comparator.comparingDouble(metric))
                .orElseThrow();
    }

    public static <T> T findMax(List<T> records, ToDoubleFunction<T> metric) {
        return records.stream()
                .max(Comparator.comparingDouble(metric))
                .orElseThrow();
    }
}

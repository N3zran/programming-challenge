package de.bcxp.challenge.domain.model;

/**
 * Represents a single day's weather data with temperature readings.
 */
public class WeatherRecord {

    private final int day;
    private final double maxTemperature;
    private final double minTemperature;

    public WeatherRecord(int day, double maxTemperature, double minTemperature) {
        this.day = day;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public int getDay() {
        return day;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    /**
     * Calculates the temperature spread (min / max difference).
     *
     * @return the temperature spread
     */
    public double getTemperatureSpread() {
        return maxTemperature - minTemperature;
    }

    @Override
    public String toString() {
        return String.format("WeatherRecord{day=%d, max=%.1f, min=%.1f, spread=%.1f}",
                day, maxTemperature, minTemperature, getTemperatureSpread());
    }
}

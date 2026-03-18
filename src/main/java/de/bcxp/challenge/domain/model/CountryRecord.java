package de.bcxp.challenge.domain.model;

/**
 * Represents a country with population and area data for density calculation.
 */
public class CountryRecord {

    private final String name;
    private final long population;
    private final double areaInSquareKm;

    public CountryRecord(String name, long population, double areaInSquareKm) {
        this.name = name;
        this.population = population;
        this.areaInSquareKm = areaInSquareKm;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public double getAreaInSquareKm() {
        return areaInSquareKm;
    }

    /**
     * Calculates the population density (people per square kilometre).
     *
     * @return the population density
     * @throws ArithmeticException if area is zero
     */
    public double getPopulationDensity() {
        return population / areaInSquareKm;
    }

    @Override
    public String toString() {
        return String.format("CountryRecord{name='%s', population=%d, area=%.0f km², density=%.2f}",
                name, population, areaInSquareKm, getPopulationDensity());
    }
}

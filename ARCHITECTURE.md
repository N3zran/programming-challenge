# Architecture Overview

---

## Component Diagram

```
App.java
  │
  ├──► WeatherAnalysisService ──┬──► TemperatureSpreadCalculator ──► ExtremumSelector
  │         (application)       │         (domain/service)              (domain/service)
  │                             │
  │                             └──► DataReader<WeatherRecord>
  │                                       │ (interface)
  │                                       │
  │                                       └── CsvFileReader ──► WeatherRowMapper
  │                                            (infrastructure)    (infrastructure)
  │
  └──► CountryAnalysisService ──┬──► PopulationDensityCalculator ──► ExtremumSelector
            (application)       │         (domain/service)              (domain/service)
                                │
                                └──► DataReader<CountryRecord>
                                          │ (interface)
                                          │
                                          └── CsvFileReader ──► CountryRowMapper
                                               (infrastructure)    (infrastructure)
```

---
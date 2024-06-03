package Clases;

import java.util.Map;
import java.util.TreeMap;

public record Moneda(String base_code, TreeMap<String,Double> conversion_rates) {
}

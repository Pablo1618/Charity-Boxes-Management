package pablo.charity_boxes;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public enum Currency {
    PLN, EUR, USD;

    private static final Map<Currency, Map<Currency, BigDecimal>> exchangeRates = new HashMap<>();

    static {
        Map<Currency, BigDecimal> plnRates = new HashMap<>();
        plnRates.put(PLN, BigDecimal.ONE);
        plnRates.put(EUR, new BigDecimal("0.24"));
        plnRates.put(USD, new BigDecimal("0.27"));
        exchangeRates.put(PLN, plnRates);

        Map<Currency, BigDecimal> eurRates = new HashMap<>();
        eurRates.put(PLN, new BigDecimal("4.23"));
        eurRates.put(EUR, BigDecimal.ONE);
        eurRates.put(USD, new BigDecimal("1.13"));
        exchangeRates.put(EUR, eurRates);

        Map<Currency, BigDecimal> usdRates = new HashMap<>();
        usdRates.put(PLN, new BigDecimal("3.76"));
        usdRates.put(EUR, new BigDecimal("0.89"));
        usdRates.put(USD, BigDecimal.ONE);
        exchangeRates.put(USD, usdRates);
    }

    public static BigDecimal getExchangeRate(Currency from, Currency to) {
        return exchangeRates.get(from).get(to);
    }
}

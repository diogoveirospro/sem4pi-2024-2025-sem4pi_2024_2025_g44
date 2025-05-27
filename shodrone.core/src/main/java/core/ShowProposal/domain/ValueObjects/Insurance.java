package core.ShowProposal.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Map;

@Embeddable
public class Insurance implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private String amount;

    private String currencySymbol;

    protected Insurance() {
        // for ORM
    }

    public Insurance(String amount,  String currencySymbol) {
        validateArguments(amount, currencySymbol);
        this.amount = amount;
        this.currencySymbol = currencySymbol;
    }

    public String obtainAmount() {
        return amount;
    }

    public String obtainCurrencySymbol() {
        return currencySymbol;
    }

    private static final Map<String, String> MAIN_EUROPEAN_CURRENCIES = Map.ofEntries(
            Map.entry("EUR - Euro", "€"),
            Map.entry("GBP - British Pound Sterling", "£"),
            Map.entry("CHF - Swiss Franc", "CHF"),
            Map.entry("SEK - Swedish Krona", "kr"),
            Map.entry("NOK - Norwegian Krone", "kr"),
            Map.entry("DKK - Danish Krone", "kr"),
            Map.entry("PLN - Polish Złoty", "zł"),
            Map.entry("HUF - Hungarian Forint", "Ft."),
            Map.entry("CZK - Czech Koruna", "Kč"),
            Map.entry("RUB - Russian Ruble", "₽"),
            Map.entry("TRY - Turkish Lira", "₺"),
            Map.entry("UAH - Ukrainian Hryvnia", "₴")
    );

    public static Map<String, String> getMainEuropeanCurrencies() {
        return MAIN_EUROPEAN_CURRENCIES;
    }

    private void validateArguments(String amount, String currencySymbol) {
        if (amount == null || currencySymbol == null) {
            throw new IllegalArgumentException("Amount and currency code must not be null");
        }
        if (!amount.matches("\\d+(\\.\\d{1,2})?")) {
            throw new IllegalArgumentException("Amount must be a valid number with up to two decimal places");
        }
        if (Double.parseDouble(amount) < 0) {
            throw new IllegalArgumentException("Amount must not be negative");
        }
        for ( String code : MAIN_EUROPEAN_CURRENCIES.keySet()) {
            if (MAIN_EUROPEAN_CURRENCIES.get(code).equals(currencySymbol)) {
                return;
            }
        }
        throw new IllegalArgumentException("Currency symbol is not valid for main European currencies");
    }

    @Override
    public String toString() {
        return amount + " " + currencySymbol;
    }
}
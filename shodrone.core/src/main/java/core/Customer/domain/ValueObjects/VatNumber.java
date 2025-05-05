package core.Customer.domain.ValueObjects;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a European VAT number with validation based on known country codes.
 */
@Embeddable
public class VatNumber implements Comparable<VatNumber>, Serializable {

    private static final long serialVersionUID = 1L;

    protected VatNumber() {
    }

    private static final Map<String, String> VAT_COUNTRY_CODES = Map.ofEntries(
            Map.entry("AL", "Albania"),
            Map.entry("AD", "Andorra"),
            Map.entry("AM", "Armenia"),
            Map.entry("AT", "Austria"),
            Map.entry("AZ", "Azerbaijan"),
            Map.entry("BY", "Belarus"),
            Map.entry("BE", "Belgium"),
            Map.entry("BA", "Bosnia and Herzegovina"),
            Map.entry("BG", "Bulgaria"),
            Map.entry("HR", "Croatia"),
            Map.entry("CY", "Cyprus"),
            Map.entry("CZ", "Czech Republic"),
            Map.entry("DK", "Denmark"),
            Map.entry("EE", "Estonia"),
            Map.entry("FI", "Finland"),
            Map.entry("FR", "France"),
            Map.entry("GE", "Georgia"),
            Map.entry("DE", "Germany"),
            Map.entry("GR", "Greece"),
            Map.entry("HU", "Hungary"),
            Map.entry("IS", "Iceland"),
            Map.entry("IE", "Ireland"),
            Map.entry("IT", "Italy"),
            Map.entry("XK", "Kosovo"),
            Map.entry("LV", "Latvia"),
            Map.entry("LI", "Liechtenstein"),
            Map.entry("LT", "Lithuania"),
            Map.entry("LU", "Luxembourg"),
            Map.entry("MT", "Malta"),
            Map.entry("MD", "Moldova"),
            Map.entry("MC", "Monaco"),
            Map.entry("ME", "Montenegro"),
            Map.entry("NL", "Netherlands"),
            Map.entry("MK", "North Macedonia"),
            Map.entry("NO", "Norway"),
            Map.entry("PL", "Poland"),
            Map.entry("PT", "Portugal"),
            Map.entry("RO", "Romania"),
            Map.entry("RU", "Russia"),
            Map.entry("SM", "San Marino"),
            Map.entry("RS", "Serbia"),
            Map.entry("SK", "Slovakia"),
            Map.entry("SI", "Slovenia"),
            Map.entry("ES", "Spain"),
            Map.entry("SE", "Sweden"),
            Map.entry("CH", "Switzerland"),
            Map.entry("TR", "Turkey"),
            Map.entry("UA", "Ukraine"),
            Map.entry("GB", "United Kingdom"),
            Map.entry("VA", "Vatican City")
    );

    private String countryCode;
    private String countryName;

    public VatNumber(String vatNumber) {
        if (vatNumber == null || vatNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("VAT Number cannot be null or empty");
        }

        vatNumber = vatNumber.trim().toUpperCase();

        if (vatNumber.length() < 3) {
            throw new IllegalArgumentException("VAT Number is too short");
        }

        String prefix = vatNumber.substring(0, 2);
        if (!VAT_COUNTRY_CODES.containsKey(prefix)) {
            throw new IllegalArgumentException("Invalid VAT country prefix: " + prefix);
        }

        if (!vatNumber.matches("[A-Z0-9]+")) {
            throw new IllegalArgumentException("VAT Number contains invalid characters");
        }

        if (vatNumber.length() > 15) {
            throw new IllegalArgumentException("VAT Number is too long");
        }

        this.countryCode = prefix;
        this.countryName = VAT_COUNTRY_CODES.get(prefix);
    }

    public String countryCode() {
        return countryCode;
    }

    public String countryName() {
        return countryName;
    }

    @Override
    public int compareTo(VatNumber other) {
        return this.countryCode.compareTo(other.countryCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VatNumber)) return false;
        VatNumber that = (VatNumber) o;
        return Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(countryName, that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, countryName);
    }

    @Override
    public String toString() {
        return countryCode + " - " + countryName;
    }

    public static Map<String, String> getVatCountryCodes() {
        return VAT_COUNTRY_CODES;
    }
}

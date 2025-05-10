package core.Customer.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a European VAT number with validation based on known country codes.
 */
@Embeddable
public class VatNumber implements Comparable<VatNumber>, Serializable, ValueObject {

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
    private String vatNumber; // only the numeric part

    public VatNumber(String fullVat) {
        if (fullVat == null || fullVat.trim().isEmpty()) {
            throw new IllegalArgumentException("VAT Number cannot be null or empty");
        }

        fullVat = fullVat.trim().toUpperCase();

        for (char c : fullVat.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                throw new IllegalArgumentException("VAT Number can only contain letters and digits, and no spaces");
            }
        }
        if (fullVat.length() < 5) {
            throw new IllegalArgumentException("VAT Number is too short");
        }

        String prefix = fullVat.substring(0, 2);
        if (!VAT_COUNTRY_CODES.containsKey(prefix)) {
            throw new IllegalArgumentException("Invalid VAT country prefix: " + prefix);
        }
        if(!prefix.matches("[A-Z]{2}")) {
            throw new IllegalArgumentException("VAT country prefix must be two uppercase letters");
        }

        String numberPart = fullVat.substring(2);
        if (!numberPart.matches("[0-9]+")) {
            throw new IllegalArgumentException("VAT number must contain only digits after country code");
        }

        if (numberPart.length() > 13) {
            throw new IllegalArgumentException("VAT number is too long");
        }



        this.countryCode = prefix;
        this.countryName = VAT_COUNTRY_CODES.get(prefix);
        this.vatNumber = numberPart;
    }

    public String countryCode() {
        return countryCode;
    }

    public String countryName() {
        return countryName;
    }

    public String vatNumber() {
        return vatNumber;
    }

    public String fullVatNumber() {
        return countryCode + vatNumber;
    }

    @Override
    public int compareTo(VatNumber other) {
        return this.fullVatNumber().compareTo(other.fullVatNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VatNumber)) return false;
        VatNumber that = (VatNumber) o;
        return Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(vatNumber, that.vatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, vatNumber);
    }

    @Override
    public String toString() {
        return fullVatNumber();
    }

    public static Map<String, String> getVatCountryCodes() {
        return VAT_COUNTRY_CODES;
    }

    public static String VatCode(String country) {
        for (Map.Entry<String, String> entry : VAT_COUNTRY_CODES.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(country)) {
                return entry.getKey();
            }
        }
        return null;
    }
}

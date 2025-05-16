package core.Shared.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a phone number with a country code and national number.
 * This class is immutable and implements ValueObject interface.
 */
@Embeddable
public class PhoneNumber implements Serializable, ValueObject {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * A map of european country codes and their corresponding country names.
     * This map is static and final, meaning it cannot be modified after initialization.
     */
    private static final Map<String, String> COUNTRY_CODES = Map.ofEntries(
            Map.entry("+355", "Albania"),
            Map.entry("+376", "Andorra"),
            Map.entry("+374", "Armenia"),
            Map.entry("+43", "Austria"),
            Map.entry("+994", "Azerbaijan"),
            Map.entry("+375", "Belarus"),
            Map.entry("+32", "Belgium"),
            Map.entry("+387", "Bosnia and Herzegovina"),
            Map.entry("+359", "Bulgaria"),
            Map.entry("+385", "Croatia"),
            Map.entry("+357", "Cyprus"),
            Map.entry("+420", "Czech Republic"),
            Map.entry("+45", "Denmark"),
            Map.entry("+372", "Estonia"),
            Map.entry("+358", "Finland"),
            Map.entry("+33", "France"),
            Map.entry("+995", "Georgia"),
            Map.entry("+49", "Germany"),
            Map.entry("+350", "Gibraltar"),
            Map.entry("+30", "Greece"),
            Map.entry("+36", "Hungary"),
            Map.entry("+354", "Iceland"),
            Map.entry("+353", "Ireland"),
            Map.entry("+39", "Italy"),
            Map.entry("+383", "Kosovo"),
            Map.entry("+371", "Latvia"),
            Map.entry("+423", "Liechtenstein"),
            Map.entry("+370", "Lithuania"),
            Map.entry("+352", "Luxembourg"),
            Map.entry("+356", "Malta"),
            Map.entry("+373", "Moldova"),
            Map.entry("+377", "Monaco"),
            Map.entry("+382", "Montenegro"),
            Map.entry("+31", "Netherlands"),
            Map.entry("+389", "North Macedonia"),
            Map.entry("+47", "Norway"),
            Map.entry("+48", "Poland"),
            Map.entry("+351", "Portugal"),
            Map.entry("+40", "Romania"),
            Map.entry("+7", "Russia"),
            Map.entry("+378", "San Marino"),
            Map.entry("+381", "Serbia"),
            Map.entry("+421", "Slovakia"),
            Map.entry("+386", "Slovenia"),
            Map.entry("+34", "Spain"),
            Map.entry("+46", "Sweden"),
            Map.entry("+41", "Switzerland"),
            Map.entry("+90", "Turkey"),
            Map.entry("+380", "Ukraine"),
            Map.entry("+44", "United Kingdom"),
            Map.entry("+379", "Vatican City")
    );

    /**
     * The country code of the phone number.
     */
    private String countryCode;

    /**
     * The national number of the phone number.
     */
    private String nationalNumber;

    /**
     * Default constructor for JPA.
     *
     */
    protected PhoneNumber() {
        this.countryCode = null;
        this.nationalNumber = null;
    }

    /**
     * Creates a PhoneNumber object with the specified country code and national number.
     * Throws IllegalArgumentException if the country code or national number is invalid.
     *
     * @param countryCode the country code of the phone number
     * @param nationalNumber the national number of the phone number
     */
    public PhoneNumber(final String countryCode, final String nationalNumber) {
        if (countryCode == null || countryCode.isEmpty()) {
            throw new IllegalArgumentException("\nCountry code cannot be null or empty! ");
        }
        if (nationalNumber == null || nationalNumber.isEmpty()) {
            throw new IllegalArgumentException("\nNational number cannot be null or empty! ");
        }
        if (!isValidCountryCode(countryCode)) {
            throw new IllegalArgumentException("\nInvalid country code! ");
        }
        if (!isValidNationalNumber(nationalNumber)) {
            throw new IllegalArgumentException("\nInvalid national number! ");
        }
        this.countryCode = countryCode;
        this.nationalNumber = nationalNumber;
    }

    /**
     * Checks if the given country code is valid.
     * @param countryCode the country code to check
     * @return true if the country code is valid, false otherwise
     */
    private boolean isValidCountryCode(String countryCode) {
        return COUNTRY_CODES.containsKey(countryCode);
    }

    /**
     * Checks if the given national number is valid.
     * @param nationalNumber the national number to check
     * @return true if the national number is valid, false otherwise
     */
    private boolean isValidNationalNumber(String nationalNumber) {
        return nationalNumber != null && nationalNumber.matches("\\d{7,15}");
    }

    /**
     * Returns the phone number without the country code.
     * @return the national number of the phone number
     */
    public String phoneNumber() {
        return nationalNumber;
    }

    /**
     * Returns the country of the phone number.
     * @return the country name corresponding to the country code
     */
    public String country(){

        if (isValidCountryCode(countryCode)) {
            return COUNTRY_CODES.get(countryCode);
        }
        return null;
    }

    /**
     * Returns the country code of a country.
     * @return the country code of the phone number
     */

    public static String countryCodeOfCountry(String country) {
        if (country == null || country.isEmpty()) {
            return null;
        }
        for (Map.Entry<String, String> entry : COUNTRY_CODES.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(country)) {
                return entry.getKey();
            }
        }
        return null;
    }


    /**
     * Returns the phone number with the country code.
     * @return the full phone number including the country code
     */
    @Override
    public String toString(){
        return countryCode + " " + nationalNumber;
    }

    /**
     * equals method to compare two PhoneNumber objects.
     * @param other the object to compare with this object
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        PhoneNumber that = (PhoneNumber) other;

        if (!countryCode.equals(that.countryCode)) return false;
        return nationalNumber.equals(that.nationalNumber);
    }

    /**
     * hashCode method to generate a hash code for the PhoneNumber object.
     * @return the hash code for the PhoneNumber object
     */
    @Override
    public int hashCode(){
        int PRIME = 59;
        int result = 1;
        result = result * PRIME + countryCode.hashCode();
        result = result * PRIME + nationalNumber.hashCode();
        return result;
    }

    /**
     * The map of country codes and their corresponding country names is static and final,
     *
     */
    public static Map<String, String> countryCodes() {
        Map<String, String> sortedMap = new TreeMap<>(Comparator.comparing(COUNTRY_CODES::get, String.CASE_INSENSITIVE_ORDER));
        sortedMap.putAll(COUNTRY_CODES);

        return sortedMap;
    }
}

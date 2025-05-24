package core.ShowProposal.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.money.domain.model.Money;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;

@Embeddable
public class Insurance implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal amount;

    private Currency currency;

    protected Insurance() {
        // for ORM
    }

    public Insurance(BigDecimal amount, Currency currency) {
        validateArguments(amount, currency);
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal obtainAmount() {
        return amount;
    }

    public Currency obtainCurrency() {
        return currency;
    }

    private void validateArguments(Object amount, Currency currency) {
        if (amount == null || currency == null) {
            throw new IllegalArgumentException("Amount and currency must not be null");
        }
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
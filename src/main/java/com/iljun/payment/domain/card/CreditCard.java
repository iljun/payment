package com.iljun.payment.domain.card;

import com.iljun.payment.domain.payment.dto.PaymentRequestDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreditCard {
    private static final String REGX_PATTERN = "^[0-9]+$";
    private static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("MMyy")
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String cvc;

    @Column(nullable = false)
    private String validity;

    public CreditCard(String cardNumber, String cvc, String validity) {
        validate(cardNumber, cvc, validity);
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.validity = validity;
    }

    public CreditCard(PaymentRequestDto paymentRequestDto) {
        this(paymentRequestDto.getCreditCardNumber(), paymentRequestDto.getCvc(), paymentRequestDto.getValidity());
    }

    private void validate(String cardNumber, String cvc, String validity) {
        validateCardNumber(cardNumber);
        validateCvc(cvc);
        validateValidity(validity);
    }

    private void validateCardNumber(String cardNumber) {
        if (Objects.isNull(cardNumber) || !(cardNumber.length() == 10 || cardNumber.length() == 16)) {
            throw new CreditCardException("cardNumber is not valid");
        }

        if (!cardNumber.matches(REGX_PATTERN)) {
            throw new CreditCardException("cardNumber only allow number");
        }
    }

    private void validateCvc(String cvc) {
        if (Objects.isNull(cvc) || cvc.length() != 4) {
            throw new CreditCardException("cvc is not valid");
        }
    }

    private void validateValidity(String validity) {
        if (Objects.isNull(validity) || validity.length() != 4) {
            throw new CreditCardException("validity is not valid");
        }
    }

    public void checkValidity() {
        LocalDateTime localDateTime = LocalDateTime.parse(this.validity, DATE_FORMATTER);
        if (localDateTime.isBefore(LocalDateTime.now())) {
            throw new CreditCardException("validity is expired");
        }
    }
}

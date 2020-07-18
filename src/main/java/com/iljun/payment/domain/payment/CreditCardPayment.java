package com.iljun.payment.domain.payment;

import com.iljun.payment.domain.card.CreditCard;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import java.math.BigDecimal;

@DiscriminatorValue("CREDIT_CARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CreditCardPayment {

    @Embedded
    private CreditCard creditCard;

    @Column(name = "tax")
    private BigDecimal tax;
}

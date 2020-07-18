package com.iljun.payment.domain.card;

import javax.persistence.Embeddable;

@Embeddable
public class CreditCard {

    private String cardNumber;

    private String cvc;

    private String validity;
}

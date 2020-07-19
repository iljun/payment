package com.iljun.payment.domain.card;

public class CreditCardException extends RuntimeException {
    public CreditCardException(String message) {
        super(message);
    }
}

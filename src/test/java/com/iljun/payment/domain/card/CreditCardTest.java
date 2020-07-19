package com.iljun.payment.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CreditCardTest {

    private CreditCard create(String creditCardNumber, String validity, String cvc) {
        return new CreditCard(creditCardNumber, cvc, validity);
    }

    @ParameterizedTest
    @MethodSource("provideCreditCardInformation")
    @DisplayName("신용카드 객체 생성 테스트")
    void createCreditCard(String creditCardNumber, String validity, String cvc) {
        assertThatCode(() -> create(creditCardNumber, validity, cvc));
    }

    private static Stream<Arguments> provideCreditCardInformation() {
        return Stream.of(
                Arguments.of("1005912334561234", "0614", "294"),
                Arguments.of("1234456723", "1234", "345"),
                Arguments.of("1003921368501424", "2205", "363")
        );
    }

    @ParameterizedTest
    @MethodSource("provideNotValidCardNumber")
    @DisplayName("카드번호 유효성 검사 테스트")
    void validateCardNumber(String cardNumber) {
        assertThatThrownBy(() -> create(cardNumber, "1234", "1233"))
                .isInstanceOf(CreditCardException.class);
    }

    private static Stream<Arguments> provideNotValidCardNumber() {
        return Stream.of(
                Arguments.of("1111111111111"),
                Arguments.of(""),
                Arguments.of("123"),
                Arguments.of("123-345")
        );
    }

    @ParameterizedTest
    @MethodSource("provideNotValidCvc")
    @DisplayName("cvc 유효성 검사 테스트")
    void validateCvc(String cvc) {
        assertThatThrownBy(() -> create("1003921368501424", "1234", cvc))
                .isInstanceOf(CreditCardException.class);
    }

    private static Stream<Arguments> provideNotValidCvc() {
        return Stream.of(
                Arguments.of("12"),
                Arguments.of("0"),
                Arguments.of("123")
        );
    }

    @ParameterizedTest
    @MethodSource("provideNotValidValidity")
    @DisplayName("유효기간 유효성 검사 테스트")
    void validateValidity(String validity) {
        assertThatThrownBy(() -> create("1003921368501424", validity, "1233"))
                .isInstanceOf(CreditCardException.class);
    }

    private static Stream<Arguments> provideNotValidValidity() {
        return Stream.of(
                Arguments.of("234"),
                Arguments.of("3"),
                Arguments.of("12"),
                Arguments.of("0000")
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidity")
    @DisplayName("유효 기간이 정상인 경우 테스트")
    void checkExpiredValidity(String validity) {
        CreditCard creditCard = create("1003921368501424", validity, "1233");
        assertThatCode(() -> creditCard.checkValidity());
    }

    private static Stream<Arguments> provideValidity() {
        return Stream.of(
                Arguments.of("0522"),
                Arguments.of("0321"),
                Arguments.of("1220")
        );
    }

    @ParameterizedTest
    @MethodSource("provideExpiredValidValidity")
    @DisplayName("유효 기간이 지난 경우 Exception test")
    void checkExpiredValidityException(String validity) {
        CreditCard creditCard = create("1111111111111111", validity, "1233");
        assertThatThrownBy(() -> creditCard.checkValidity())
                .isInstanceOf(CreditCardException.class);
    }

    private static Stream<Arguments> provideExpiredValidValidity() {
        return Stream.of(
                Arguments.of("0518"),
                Arguments.of("0420"),
                Arguments.of("1203")
        );
    }
}
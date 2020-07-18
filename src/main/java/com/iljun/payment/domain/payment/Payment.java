package com.iljun.payment.domain.payment;

import com.iljun.payment.domain.payment.cancel.CancelHistories;
import com.iljun.payment.domain.support.ModifiedAt;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "payment")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends ModifiedAt {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Embedded
    private CancelHistories cancelHistories;

    @PrePersist
    public void generateId() {
        String uuid = UUID.randomUUID().toString();
        this.id = uuid.replaceAll("-", "");
    }
}

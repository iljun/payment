package com.iljun.payment.domain.payment.cancel;

import com.iljun.payment.domain.support.CreatedAt;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "cancel_history")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CancelHistory extends CreatedAt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "tax")
    private BigDecimal tax;
}

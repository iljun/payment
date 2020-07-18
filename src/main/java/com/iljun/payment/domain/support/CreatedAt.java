package com.iljun.payment.domain.support;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class CreatedAt {

    private LocalDateTime createdAt;

    @PrePersist
    public void initCreated() {
        this.createdAt = LocalDateTime.now();
    }
}

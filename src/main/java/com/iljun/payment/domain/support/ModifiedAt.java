package com.iljun.payment.domain.support;

import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class ModifiedAt extends CreatedAt {

    private LocalDateTime modifiedAt;

    @PreUpdate
    public void initCreated() {
        this.modifiedAt = LocalDateTime.now();
    }
}

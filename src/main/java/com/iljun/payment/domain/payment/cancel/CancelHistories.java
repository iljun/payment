package com.iljun.payment.domain.payment.cancel;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class CancelHistories {

    @OneToMany(mappedBy = "payment")
    private List<CancelHistory> cancelHistories = new ArrayList<>();
}

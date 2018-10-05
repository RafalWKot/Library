package com.crud.library.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PenaltyFee {
    BigDecimal PENALTYVALUE = BigDecimal.valueOf(2.0);

    BigDecimal calculatePenaltyFee(LocalDateTime borrowDate, LocalDateTime returnDate);
}

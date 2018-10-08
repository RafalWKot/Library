package com.crud.library.service.impl;

import com.crud.library.service.PenaltyFee;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;

@Service
public class PenaltyFeeImpl implements PenaltyFee {

    @Override
    public BigDecimal calculatePenaltyFee(LocalDateTime plannedReturnDate, LocalDateTime returnDate) {
        return BigDecimal.valueOf(Period
                                .between(plannedReturnDate.toLocalDate(), returnDate.toLocalDate())
                                .getDays())
                        .movePointLeft(2)
                        .multiply((PENALTYVALUE));
    }
}

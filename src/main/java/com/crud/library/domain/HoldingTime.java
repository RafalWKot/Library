package com.crud.library.domain;

import java.math.BigDecimal;

public class HoldingTime {

    public static BigDecimal PENALTYFEE = BigDecimal.valueOf(2.0);

    public static Long addTime(int weeks) {
        return Long.valueOf(7 * weeks);
    }
}

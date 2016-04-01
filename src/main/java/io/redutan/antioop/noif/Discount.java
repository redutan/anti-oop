package io.redutan.antioop.noif;

import lombok.Value;

/**
 * 할인VO
 * Created by redutan on 2016. 4. 1..
 */
@Value(staticConstructor = "of")
public class Discount {
    /** 할인금액 */
    private long discountAmt;
}

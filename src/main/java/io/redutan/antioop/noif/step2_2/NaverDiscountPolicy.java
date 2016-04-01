package io.redutan.antioop.noif.step2_2;

import io.redutan.antioop.noif.Discountable;

/**
 * Created by redutan on 2016. 4. 1..
 */
class NaverDiscountPolicy implements Discountable {
    @Override
    public long getDiscountAmt(long originAmt) {
        return (long)(originAmt * 0.1);
    }
}

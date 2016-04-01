package io.redutan.antioop.noif.step_enum;

import io.redutan.antioop.noif.Discountable;

/**
 * Created by redutan on 2016. 4. 1..
 */
public enum DiscountPolicy implements Discountable {
    /** 네이버 할인 */
    NAVER(10, 0L) {
        @Override
        public long getDiscountAmt(long originAmt) {
            return originAmt * this.discountRate / 100;
        }
    },
    /** 다나와 할인 */
    DANAWA(15, 0L) {
        @Override
        public long getDiscountAmt(long originAmt) {
            return originAmt * this.discountRate / 100;
        }
    },
    /** 팬카페 할인 */
    FANCAFE(0, 1000L) {
        @Override
        public long getDiscountAmt(long originAmt) {
            if (originAmt < this.discountAmt)
                return originAmt;
            return this.discountAmt;
        }
    }
    ;
    final int discountRate;
    final long discountAmt;

    DiscountPolicy(int discountRate, long discountAmt) {
        this.discountRate = discountRate;
        this.discountAmt = discountAmt;
    }
}

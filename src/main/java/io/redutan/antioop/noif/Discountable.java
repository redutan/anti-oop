package io.redutan.antioop.noif;

/**
 * 할인 핵심 인터페이스
 * Created by redutan on 2016. 4. 1..
 */
public interface Discountable {
    /** 할인없음 */
    Discountable NONE = (originAmt) -> 0;

    /** 할인금액 반환 */
    long getDiscountAmt(long originAmt);
}

package io.redutan.antioop.noif;

import lombok.Value;

/**
 * 할인조회 파라메터
 * Created by redutan on 2016. 4. 1..
 */
@Value
public class DiscountRequest {
    /** 할인코드 (NAVER:네이버검색-10%, DANAWA:다나와검색-15% FANCAFE:팬카페-1000원) */
    private String discountCode;
    /** 상품금액 */
    private long productAmt;
}

package io.redutan.antioop.noif.step_enum;

import io.redutan.antioop.noif.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by redutan on 2016. 4. 1..
 */
@Slf4j
public class PaymentServiceEnum implements PaymentService {
    // 실시간 할인내역 확인
    @Override
    public Discount getDiscount(DiscountRequest request) {
        // 상품금액
        long productAmt = request.getProductAmt();
        // 할인코드 (NAVER:네이버검색-10%, DANAWA:다나와검색-15% FANCAFE:팬카페-1000원)
        String discountCode = request.getDiscountCode();
        // 할인정책
        Discountable discountPolicy = getDiscounter(discountCode);

        // 할인금액
        long discountAmt = discountPolicy.getDiscountAmt(productAmt);
        return Discount.of(discountAmt);
    }

    // 결제처리
    @Override
    public void payment(PaymentRequest request) {
        // 상품금액
        long productAmt = request.getProductAmt();
        // 할인코드 (NAVER:네이버검색-10%, DANAWA:다나와검색-15% FANCAFE:팬카페-1000원)
        String discountCode = request.getDiscountCode();
        // 할인정책
        Discountable discountPolicy = getDiscounter(discountCode);

        // 결제금액
        long paymentAmt = productAmt - discountPolicy.getDiscountAmt(productAmt);
        // TODO Something...
    }

    // 팩토리 메서드
    private Discountable getDiscounter(String discountCode) {
        if (discountCode == null)
            return Discountable.NONE;
        try {
            return DiscountPolicy.valueOf(discountCode);
        } catch (IllegalArgumentException iae) {
            log.warn("Not found discountCode : {}", discountCode);
            return Discountable.NONE;
        }
    }
}

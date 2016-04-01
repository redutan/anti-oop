package io.redutan.antioop.noif.step1;

import io.redutan.antioop.noif.Discount;
import io.redutan.antioop.noif.DiscountRequest;
import io.redutan.antioop.noif.PaymentRequest;
import io.redutan.antioop.noif.PaymentService;

/**
 * Created by redutan on 2016. 4. 1..
 */
public class PaymentService1 implements PaymentService {
    @Override
    // 실시간 할인내역 확인
    public Discount getDiscount(DiscountRequest request) {
        // 상품금액
        long productAmt = request.getProductAmt();
        // 할인코드 (NAVER:네이버검색-10%, DANAWA:다나와검색-15% FANCAFE:팬카페-1000원)
        String discountCode = request.getDiscountCode();

        // 할인금액
        long discountAmt = getDiscountAmt(discountCode, productAmt);
        return Discount.of(discountAmt);
    }

    @Override
    // 결제처리
    public void payment(PaymentRequest request) {
        // 상품금액
        long productAmt = request.getProductAmt();
        // 할인코드 (NAVER:네이버검색-10%, DANAWA:다나와검색-15% FANCAFE:팬카페-1000원)
        String discountCode = request.getDiscountCode();

        // 결제금액
        long paymentAmt = productAmt - getDiscountAmt(discountCode, productAmt);
        // TODO Something...
    }

    private long getDiscountAmt(String discountCode, long productAmt) {
        long discountAmt = 0;
        if ("NAVER".equals(discountCode)) {   // 네이버검색 할인
            discountAmt = (long)(productAmt * 0.1);
        } else if ("DANAWA".equals(discountCode)) { // 다나와검색 할인
            discountAmt = (long)(productAmt * 0.15);
        } else if ("FANCAFE".equals(discountCode)) {  // 팬카페인입 할인
            if (productAmt < 1000)  // 할인쿠폰 금액보다 적은경우
                discountAmt = productAmt;
            else
                discountAmt = 1000;
        }
        return discountAmt;
    }
}

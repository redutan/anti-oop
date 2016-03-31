package io.redutan.antioop.noif.step2_2;

import io.redutan.antioop.noif.Discountable;
import io.redutan.antioop.noif.DiscounterFactory;

/**
 * Created by redutan on 2016. 4. 1..
 */
public class SimpleDiscounterFactory implements DiscounterFactory {
    @Override
    public Discountable getDiscounter(String discountCode) {
        if ("NAVER".equals(discountCode)) {   // 네이버검색 할인
            return new NaverDiscountPolicy();
        } else if ("DANAWA".equals(discountCode)) { // 다나와검색 할인
            return new DanawaDiscountPolicy();
        } else if ("FANCAFE".equals(discountCode)) {  // 팬카페 할인
            return new FancafeDiscountPolicy();
        } else {
            return Discountable.NONE;
        }
    }
}

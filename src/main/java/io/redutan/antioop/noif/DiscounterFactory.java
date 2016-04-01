package io.redutan.antioop.noif;

/**
 * 할인 생성 팩토리
 * Created by redutan on 2016. 4. 1..
 */
public interface DiscounterFactory {
    /** 할인 생성 */
    Discountable getDiscounter(String discountCode);
}

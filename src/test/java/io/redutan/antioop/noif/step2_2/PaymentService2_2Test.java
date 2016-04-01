package io.redutan.antioop.noif.step2_2;

import io.redutan.antioop.noif.Discount;
import io.redutan.antioop.noif.DiscountRequest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by redutan on 2016. 4. 1..
 */
public class PaymentService2_2Test {
    PaymentService2_2 service;

    @Before
    public void setUp() throws Exception {
        service = new PaymentService2_2(new SimpleDiscounterFactory());
    }

    @Test
    public void getDiscount_Naver() throws Exception {
        // Given
        DiscountRequest request1 = new DiscountRequest("NAVER", 20000);
        // When
        Discount discount1 = service.getDiscount(request1);
        // Then
        assertThat(discount1.getDiscountAmt(), is(2000L));
    }

    @Test
    public void getDiscount_Danawa() throws Exception {
        // Given
        DiscountRequest request1 = new DiscountRequest("DANAWA", 20000);
        // When
        Discount discount1 = service.getDiscount(request1);
        // Then
        assertThat(discount1.getDiscountAmt(), is(3000L));
    }

    @Test
    public void getDiscount_Fancafe() throws Exception {
        // Given
        DiscountRequest request1 = new DiscountRequest("FANCAFE", 20000);
        // When
        Discount discount1 = service.getDiscount(request1);
        // Then
        assertThat(discount1.getDiscountAmt(), is(1000L));
    }

    @Test
    public void getDiscount_FancafeLessThenAmt() throws Exception {
        // Given
        DiscountRequest request1 = new DiscountRequest("FANCAFE", 500L);
        // When
        Discount discount1 = service.getDiscount(request1);
        // Then
        assertThat(discount1.getDiscountAmt(), is(500L));
    }

    @Test
    public void getDiscount_EmptyAndNull() throws Exception {
        // Given
        DiscountRequest request1 = new DiscountRequest("", 10000);
        // When empty
        Discount discount1 = service.getDiscount(request1);
        // Then
        assertThat(discount1.getDiscountAmt(), is(0L));

        // Given
        DiscountRequest request2 = new DiscountRequest(null, 10000);
        // When null
        Discount discount2 = service.getDiscount(request2);
        // Then
        assertThat(discount2.getDiscountAmt(), is(0L));
    }

    @Test
    public void getDiscount_Invalid() throws Exception {
        // Given
        DiscountRequest request1 = new DiscountRequest("ABCDEFG", 10000);
        // When
        Discount discount1 = service.getDiscount(request1);
        // Then
        assertThat(discount1.getDiscountAmt(), is(0L));
    }
}
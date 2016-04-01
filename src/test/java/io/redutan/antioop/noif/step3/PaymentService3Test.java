package io.redutan.antioop.noif.step3;

import io.redutan.antioop.AntiOopApplication;
import io.redutan.antioop.noif.Discount;
import io.redutan.antioop.noif.DiscountRequest;
import io.redutan.antioop.noif.step3.repository.DiscounterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by redutan on 2016. 4. 1..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AntiOopApplication.class)
@Transactional
public class PaymentService3Test {
    @Autowired
    PaymentService3 service;

    @Autowired
    DiscounterRepository repository;

    @Before
    public void setUp() throws Exception {
        RateDiscounter naverDiscounter = new RateDiscounter();
        naverDiscounter.setCode("NAVER");
        naverDiscounter.setName("네이버할인");
        naverDiscounter.setRate(10);

        RateDiscounter danawaDiscounter = new RateDiscounter();
        danawaDiscounter.setCode("DANAWA");
        danawaDiscounter.setName("다나와할인");
        danawaDiscounter.setRate(15);

        AmtDiscounter fancafeDiscounter = new AmtDiscounter();
        fancafeDiscounter.setCode("FANCAFE");
        fancafeDiscounter.setName("팬카페할인");
        fancafeDiscounter.setAmt(1000);

        List<AbstractDiscounter> list = Arrays.asList(naverDiscounter, danawaDiscounter, fancafeDiscounter);

        repository.save(list);
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
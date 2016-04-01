package io.redutan.antioop.noif.step3.repository;

import io.redutan.antioop.AntiOopApplication;
import io.redutan.antioop.noif.step3.AbstractDiscounter;
import io.redutan.antioop.noif.step3.AmtDiscounter;
import io.redutan.antioop.noif.step3.RateDiscounter;
import lombok.extern.slf4j.Slf4j;
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
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by myeongju.jung on 2016. 4. 1..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AntiOopApplication.class)
@Transactional
@Slf4j
public class GenericRateDiscounterRepositoryTest {
    @Autowired
    DiscounterRepository<RateDiscounter> rateDiscounterRepository;

    @Autowired
    DiscounterRepository<AbstractDiscounter> repository;

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
    public void findByCode_Naver() throws Exception {
        // Given
        final String code = "NAVER";
        // When
        RateDiscounter discounter = rateDiscounterRepository.findByCode(code);
        log.info("{} discounter = {}", code, discounter);
        // Then
        assertDiscounter(discounter, code, 20000, 2000);
    }

    @Test
    public void findByCode_Danawa() throws Exception {
        // Given
        final String code = "DANAWA";
        // When
        RateDiscounter discounter = rateDiscounterRepository.findByCode(code);
        log.info("{} discounter = {}", code, discounter);
        // Then
        assertDiscounter(discounter, code, 20000, 3000);
    }

    /**
     * casting 오류 발생. 개인적으로 null일 발생하지 않을까 했는데 조회가 가능하긴 하다.
     * Entity가 다른데 조회가 되는 것 자체가 신기하다.
     * @throws Exception
     */
    @Test(expected = ClassCastException.class)
    public void findByCode_Fancafe() throws Exception {
        // Given
        final String code = "FANCAFE";
        // When
        RateDiscounter discounter = rateDiscounterRepository.findByCode(code);
        fail();
    }

    // RateDiscounter repository를 사용할지라도 AmtDiscounter를 조회할 수 있다. (SINGE_TABLE, JOINED 상속 전략)
    @Test
    public void findByCode_FancafeAbstract() throws Exception {
        // Given
        final String code = "FANCAFE";
        // When
        AbstractDiscounter discounter = rateDiscounterRepository.findByCode(code);
        log.info("{} discounter = {}", code, discounter);
        // Then
        assertDiscounter(discounter, code, 20000, 1000);
    }

    private void assertDiscounter(AbstractDiscounter discounter, String code, long amt, long discountAmt) {
        assertThat(discounter.getCode(), is(code));
        assertThat(discounter.getDiscountAmt(amt), is(discountAmt));
    }
}
package io.redutan.antioop.noif.step3.repository;

import io.redutan.antioop.noif.step3.AbstractDiscounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by redutan on 2016. 4. 1..
 */
@Repository
public interface DiscounterRepository<T extends AbstractDiscounter> extends JpaRepository<T, Long> {
    /**
     * 할인코드로 할인 조회
     * @param code 할인코드
     * @return
     */
    T findByCode(String code);
}

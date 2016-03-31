package io.redutan.antioop.noif.step3;

import io.redutan.antioop.noif.Discountable;
import io.redutan.antioop.noif.DiscounterFactory;
import io.redutan.antioop.noif.step3.repository.DiscounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by redutan on 2016. 4. 1..
 */
@Component
class SimpleDiscounterFactory implements DiscounterFactory {
    @Autowired
    DiscounterRepository discounterRepository;

    @Override
    public Discountable getDiscounter(String discountCode) {
        if (discountCode == null)
            return Discountable.NONE;
        AbstractDiscounter discounter = discounterRepository.findByCode(discountCode);
        return discounter == null ? Discountable.NONE : discounter;
    }
}

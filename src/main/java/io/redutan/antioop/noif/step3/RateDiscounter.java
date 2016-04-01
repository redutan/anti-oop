package io.redutan.antioop.noif.step3;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by redutan on 2016. 4. 1..
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("RATE")
public class RateDiscounter extends AbstractDiscounter {
    /** 할인율 */
    @Column
    private int rate;

    @Override
    public long getDiscountAmt(long originAmt) {
        return originAmt * rate / 100;
    }
}


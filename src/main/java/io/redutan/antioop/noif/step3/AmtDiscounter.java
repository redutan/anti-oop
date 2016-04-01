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
@DiscriminatorValue("AMT")
public class AmtDiscounter extends AbstractDiscounter {
    /** 할인금액 */
    @Column
    private long amt;

    @Override
    public long getDiscountAmt(long originAmt) {
        if (originAmt < amt)
            return originAmt;
        return amt;
    }
}


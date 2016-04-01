package io.redutan.antioop.noif.step3;

import io.redutan.antioop.noif.Discountable;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by redutan on 2016. 4. 1..
 */
@Data
@Entity
@Inheritance
public abstract class AbstractDiscounter implements Discountable {
    /** 아이디 */
    @Id
    @GeneratedValue
    @Column
    private long id;
    /** 할인코드 */
    @Column
    private String code;
    /** 할인명 */
    @Column
    private String name;
}

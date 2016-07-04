package commerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 매장
 *
 * @author chanwook
 */
@Entity
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue
    private long shopId;

    //TODO 하나의 매장이 여러 개의 카테고리에 걸릴 수 있도록 할 것인가???
    @ManyToOne
    private Category category;

}

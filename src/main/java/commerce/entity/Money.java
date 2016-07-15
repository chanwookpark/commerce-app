package commerce.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * @author chanwook
 */
@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Money {

    private long amount;

    public Money(long amount) {
        this.amount = amount;
    }

    public Money multiply(int value) {
        return new Money(amount * value);
    }
}

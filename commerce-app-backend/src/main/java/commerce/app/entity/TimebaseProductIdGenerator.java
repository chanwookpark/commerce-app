package commerce.app.entity;

/**
 * @author chanwook
 */
public class TimebaseProductIdGenerator implements ProductIdGenerator {

    @Override
    public String createId() {
        // 테스트 목적으로 쉽게 생성!
        return String.valueOf("P" + System.currentTimeMillis());
    }

}

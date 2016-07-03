package commerce.entity;

import java.time.LocalDateTime;

/**
 * @author chanwook
 */
public class OrderStatus {

    private long orderStatusId;

    private Order order;

    private OrderStatusCode code;

    private LocalDateTime updated;

    public OrderStatus() {
    }

    public OrderStatus(OrderStatusCode orderStatusCode, Order order) {
        this.code = orderStatusCode;
        this.order = order;
        this.updated = LocalDateTime.now();
    }

    public OrderStatusCode getCode() {
        return code;
    }

    public void setCode(OrderStatusCode code) {
        this.code = code;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public enum OrderStatusCode {
        // 딱히 상태 코드 명을 따기는 어려우니 쉽게 알파벳 순서로 가자고..('');;
        A/**신규주문생성(주문진행중)*/
        ,
        B/**결재완료*/
        ,
        C/**주문완료*/
        ,
        D/**환불주문생성*/
    }
}

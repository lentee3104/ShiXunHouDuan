package cn.edu.ynu.demo_app.repository;

import cn.edu.ynu.demo_app.entity.OrderTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderTableRepository extends JpaRepository<OrderTableEntity, Integer> {
    OrderTableEntity findByCustomerEntity_CustomerIdAndBusinessEntity_BusinessIdAndOrderState(Integer customer_id, Integer business_id, Integer order_state);
    OrderTableEntity save(OrderTableEntity orderTableEntity);
    OrderTableEntity findByOrderId(Integer order_id);
}

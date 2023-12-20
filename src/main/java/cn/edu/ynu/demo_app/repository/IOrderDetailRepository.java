package cn.edu.ynu.demo_app.repository;

import cn.edu.ynu.demo_app.entity.OrderDetailEntity;
import cn.edu.ynu.demo_app.entity.OrderTableEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {
    List<OrderDetailEntity> findByOrderTableEntityOrderId(Integer order_id);
    OrderDetailEntity save(OrderDetailEntity orderDetailEntity);
}

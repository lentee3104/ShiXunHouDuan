package cn.edu.ynu.demo_app.service;

import cn.edu.ynu.demo_app.entity.FoodEntity;
import cn.edu.ynu.demo_app.entity.OrderDetailEntity;
import cn.edu.ynu.demo_app.entity.OrderTableEntity;
import cn.edu.ynu.demo_app.repository.IOrderDetailRepository;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
@Component
@CommonsLog
public class OrderDetailService {
    @Resource
    private IOrderDetailRepository iOrderDetailRepository;
    private final OrderTableService orderTableService;
    private final FoodService foodService;

    public OrderDetailService(IOrderDetailRepository iOrderDetailRepository, OrderTableService orderTableService, FoodService foodService) {
        this.iOrderDetailRepository = iOrderDetailRepository;
        this.orderTableService = orderTableService;
        this.foodService = foodService;
    }

    public List<OrderDetailEntity> findByOrderTableEntityOrderId(Integer order_id){
        return iOrderDetailRepository.findByOrderTableEntityOrderId(order_id);
    }

    public OrderDetailEntity save(Integer od_id, Integer quantity, Integer food_id, Integer order_id){
        OrderTableEntity orderTableEntity = orderTableService.findByOrderId(order_id);
        FoodEntity foodEntity = foodService.findByFoodId(food_id);

        if(od_id == 0){
            OrderDetailEntity newOrderDetailEntity = OrderDetailEntity.builder()
                    .quantity(quantity)
                    .foodEntity(foodEntity)
                    .orderTableEntity(orderTableEntity)
                    .build();
            return iOrderDetailRepository.save(newOrderDetailEntity);
        }else {
            OrderDetailEntity newOrderDetailEntity = OrderDetailEntity.builder()
                    .odId(od_id)
                    .quantity(quantity)
                    .foodEntity(foodEntity)
                    .orderTableEntity(orderTableEntity)
                    .build();
            return iOrderDetailRepository.save(newOrderDetailEntity);
        }
    }
}

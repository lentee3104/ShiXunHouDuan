package cn.edu.ynu.demo_app.service;

import cn.edu.ynu.demo_app.entity.BusinessEntity;
import cn.edu.ynu.demo_app.entity.CustomerEntity;
import cn.edu.ynu.demo_app.entity.DeliveryAddressEntity;
import cn.edu.ynu.demo_app.entity.OrderTableEntity;
import cn.edu.ynu.demo_app.repository.IOrderTableRepository;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Component
@CommonsLog
@AllArgsConstructor
@NoArgsConstructor
public class OrderTableService {
    @Resource
    private IOrderTableRepository iOrderTableRepository;
    BusinessService businessService;
    CustomerService customerService;
    DeliveryAddressService deliveryAddressService;

    public OrderTableEntity findByCustomerEntity_CustomerIdAndBusinessEntity_BusinessIdAndOrderState(Integer customer_id, Integer business_id, Integer order_state){
        return iOrderTableRepository.findByCustomerEntity_CustomerIdAndBusinessEntity_BusinessIdAndOrderState(customer_id, business_id, order_state);
    }

    public OrderTableEntity findByOrderId(Integer order_id){
        return iOrderTableRepository.findByOrderId(order_id);
    }

    public OrderTableEntity save(Integer order_id, LocalDateTime order_data, Integer order_state, Double order_total, Integer business_id, Integer customer_id, Integer da_id){
        BusinessEntity businessEntity = businessService.findByBusinessId(business_id);
        CustomerEntity customerEntity = customerService.findByCustomerId(customer_id);
        DeliveryAddressEntity deliveryAddressEntity = deliveryAddressService.findByDaId(da_id);

        if(order_id == 0){
            OrderTableEntity newOrderTable = OrderTableEntity.builder()
                    .orderDate(order_data)
                    .orderState(order_state)
                    .orderTotal(order_total)
                    .businessEntity(businessEntity)
                    .customerEntity(customerEntity)
                    .deliveryAddressEntity(deliveryAddressEntity)
                    .build();
            return iOrderTableRepository.save(newOrderTable);
        }else {
            OrderTableEntity newOrderTable = OrderTableEntity.builder()
                    .orderId(order_id)
                    .orderDate(order_data)
                    .orderState(order_state)
                    .orderTotal(order_total)
                    .businessEntity(businessEntity)
                    .customerEntity(customerEntity)
                    .deliveryAddressEntity(deliveryAddressEntity)
                    .build();
            return iOrderTableRepository.save(newOrderTable);
        }
    }
}

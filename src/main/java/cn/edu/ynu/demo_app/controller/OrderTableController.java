package cn.edu.ynu.demo_app.controller;

import cn.edu.ynu.demo_app.entity.OrderTableEntity;
import cn.edu.ynu.demo_app.service.OrderTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("/public")
@Tag(name = "OrderTable", description = "商品总订单部分" )
@AllArgsConstructor
@NoArgsConstructor
public class OrderTableController {
    @Resource
    private OrderTableService orderTableService;

    @PostMapping("/FindByCustomerEntity_CustomerIdAndBusinessEntity_BusinessIdAndOrderState")
    @Operation(summary = "用customer_id、business_id、order_state查OrderTable", description = "接收Integer customer_id, Integer business_id, Integer order_state")
    public ResponseEntity<OrderTableEntity> findByCustomerEntity_CustomerIdAndBusinessEntity_BusinessIdAndOrderState(Integer customer_id, Integer business_id, Integer order_state){
        try{
            return new ResponseEntity<>(orderTableService.findByCustomerEntity_CustomerIdAndBusinessEntity_BusinessIdAndOrderState(customer_id, business_id, order_state), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/SaveOrderTableEntity")
    @Operation(summary = "用所有参数新增/更新OrderTableEntity", description = "接收Integer order_id, LocalDateTime order_data, Integer order_state, Double order_total, Integer business_id, Integer customer_id, Integer da_id")
    public ResponseEntity<OrderTableEntity> save(Integer order_id, LocalDateTime order_data, Integer order_state, Double order_total, Integer business_id, Integer customer_id, Integer da_id){
        try{
            return new ResponseEntity<>(orderTableService.save(order_id, order_data, order_state, order_total, business_id, customer_id, da_id), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

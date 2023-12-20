package cn.edu.ynu.demo_app.controller;

import cn.edu.ynu.demo_app.entity.OrderTableEntity;
import cn.edu.ynu.demo_app.service.OrderTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/orderTable")
@Tag(name = "OrderTable", description = "商品总订单部分" )
public class OrderTableController {
    @Resource
    private OrderTableService orderTableService;

    @PostMapping("/FindByUserEntityCodeAndBusinessEntity_BusinessIdAndOrderState")
    @Operation(summary = "用user_code、business_id、order_state查OrderTable", description = "接收String user_code, Integer business_id, Integer order_state")
    public ResponseEntity<OrderTableEntity> findByUserEntityCodeAndBusinessEntity_BusinessIdAndOrderState(String user_code, Integer business_id, Integer order_state){
        try{
            return new ResponseEntity<>(orderTableService.findByUserEntityCodeAndBusinessEntity_BusinessIdAndOrderState(user_code, business_id, order_state), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Modifying
    @Transactional
    @PostMapping("/SaveOrderTableEntity")
    @Operation(summary = "用所有参数新增/更新OrderTableEntity", description = "接收Integer order_id, LocalDateTime order_data, Integer order_state, Double order_total, Integer business_id, String user_code, Integer da_id")
    public ResponseEntity<OrderTableEntity> save(Integer order_id, LocalDateTime order_data, Integer order_state, Double order_total, Integer business_id, String user_code, Integer da_id){
        try{
            return new ResponseEntity<>(orderTableService.save(order_id, order_data, order_state, order_total, business_id, user_code, da_id), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

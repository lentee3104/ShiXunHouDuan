package cn.edu.ynu.demo_app.controller;

import cn.edu.ynu.demo_app.entity.OrderDetailEntity;
import cn.edu.ynu.demo_app.entity.OrderTableEntity;
import cn.edu.ynu.demo_app.service.OrderDetailService;
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

import java.util.List;

@RestController
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/orderDetail")
@Tag(name = "OrderDetail", description = "订单号、订单中有什么食物、对应食品数量的关系" )
public class OrderDetailController {
    @Resource
    private OrderDetailService orderDetailService;

    @PostMapping("/FindOrderDetailByOrderTableEntityOrderId")
    @Operation(summary = "用order_id查OrderTableEntity", description = "接收Integer的order_id")
    public ResponseEntity<List<OrderDetailEntity>> findByOrderTableEntityOrderId(Integer order_id){
        try{
            return new ResponseEntity<>(orderDetailService.findByOrderTableEntityOrderId(order_id), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Modifying
    @Transactional
    @PostMapping("/SaveOrderDetailEntity")
    @Operation(summary = "用所有参数新增/更新OrderDetailEntity", description = "接收Integer的od_id、quantity、food_id、order_id")
    public ResponseEntity<OrderDetailEntity> save(Integer od_id, Integer quantity, Integer food_id, Integer order_id){
        return new ResponseEntity<>(orderDetailService.save(od_id, quantity, food_id, order_id), HttpStatus.OK);
        /*try{

        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }*/
    }
}

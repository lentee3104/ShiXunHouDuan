package cn.edu.ynu.demo_app.controller;

import cn.edu.ynu.demo_app.entity.DeliveryAddressEntity;
import cn.edu.ynu.demo_app.service.DeliveryAddressService;
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

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/public")
@Tag(name = "Address", description = "配送地址相关的接口" )
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressController {
    @Resource
    private DeliveryAddressService deliveryAddressService;

    @PostMapping("/DeleteAddressByDaId")
    @Operation(summary = "通过地址id删除地址", description = "接收一个Integer的da_id")
    public ResponseEntity<DeliveryAddressEntity> deleteByDaId(Integer da_id){
        try{
            return new ResponseEntity<>(deliveryAddressService.deleteByDaId(da_id), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/FindAddressListByCustomerId")
    @Operation(summary = "通过customer_id查送货地址List", description = "接收一个Integer的customer_id")
    public ResponseEntity<List<DeliveryAddressEntity>> findByCustomerEntity_CustomerId(Integer customer_id){
        try{
            return new ResponseEntity<>(deliveryAddressService.findByCustomerEntity_CustomerId(customer_id), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/SaveAddress")
    @Operation(summary = "更新或保存一个Address", description = "接收所有Address的参数，如果需要新增da_id传0，如果更新，传要更新的那个地址的da_id")
    public ResponseEntity<DeliveryAddressEntity> save(Integer da_id, String contact_name, Integer contact_sex, String contact_tel, String address, Integer customer_id){
        try{
            return new ResponseEntity<>(deliveryAddressService.save(da_id, contact_name, contact_sex, contact_tel, address, customer_id), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

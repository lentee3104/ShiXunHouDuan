package cn.edu.ynu.demo_app.controller;

import cn.edu.ynu.demo_app.entity.DeliveryAddressEntity;
import cn.edu.ynu.demo_app.service.DeliveryAddressService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/address")
@Tag(name = "Address", description = "配送地址相关的接口" )
public class DeliveryAddressController {
    @Resource
    private DeliveryAddressService deliveryAddressService;

    @Modifying
    @Transactional
    @PostMapping("/DeleteAddressByDaId")
    @Operation(summary = "通过地址id删除地址", description = "接收一个Integer的da_id")
    public ResponseEntity<DeliveryAddressEntity> deleteByDaId(Integer da_id){
        try{
            return new ResponseEntity<>(deliveryAddressService.deleteByDaId(da_id), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/FindByUserEntityCode")
    @Operation(summary = "通过user_code查送货地址List", description = "接收一个String user_name")
    public ResponseEntity<List<DeliveryAddressEntity>> findByUserEntityCode(String user_code){
        try{
            return new ResponseEntity<>(deliveryAddressService.findByUserEntityCode(user_code), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Modifying
    @Transactional
    @PostMapping("/SaveAddress")
    @Operation(summary = "更新或保存一个Address", description = "需要Integer da_id, String contact_name, Integer contact_sex, String contact_tel, String address, String user_code")
    public ResponseEntity<DeliveryAddressEntity> save(@RequestParam Integer da_id, String contact_name, Integer contact_sex, String contact_tel, String address, String user_code){
        System.out.println(user_code);
        return new ResponseEntity<>(deliveryAddressService.save(da_id, contact_name, contact_sex, contact_tel, address, user_code), HttpStatus.OK);


    }
}

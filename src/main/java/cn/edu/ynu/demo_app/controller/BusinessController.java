package cn.edu.ynu.demo_app.controller;

import cn.edu.ynu.demo_app.entity.BusinessEntity;
import cn.edu.ynu.demo_app.service.BusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/public")
@Tag(name = "Business", description = "店铺相关" )
@AllArgsConstructor
@NoArgsConstructor
public class BusinessController{
    @Resource
    BusinessService businessService;

    @PostMapping("/ListAllBusiness")
    @Operation(summary = "获取所有商家信息", description = "不需要接收参数,返回一个List,里面有所有的商家对象。")
    public ResponseEntity<List<BusinessEntity>> findByBusinessIdIsNotNull(){
        try{
            return new ResponseEntity<>(businessService.findByBusinessIdIsNotNull(), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/ListBusinessByOrderTypeId")
    @Operation(summary = "用点餐类型获取商家", description = "我理解的是：点击点餐分类小图片，根据给分类图片定的点餐类型查找有什么商家")
    public ResponseEntity<List<BusinessEntity>> findByOrderTypeId(@RequestParam Integer order_type_id){
        try{
            return new ResponseEntity<>(businessService.findByOrderTypeId(order_type_id), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

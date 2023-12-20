package cn.edu.ynu.demo_app.controller;

import cn.edu.ynu.demo_app.entity.FoodEntity;
import cn.edu.ynu.demo_app.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/food")
@Tag(name = "Food", description = "商家对应售卖的食品" )
public class FoodController {
    @Resource
    private FoodService foodService;

    @PostMapping("/FindFoodListByBusinessId")
    @Operation(summary = "找某个商家下所有卖的东西", description = "接收一个Integer的business_id，返回一个FoodList")
    public ResponseEntity<List<FoodEntity>> findByBusinessEntityBusinessId(Integer business_id){
        try{
            return new ResponseEntity<>(foodService.findByBusinessEntityBusinessId(business_id), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

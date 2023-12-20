package cn.edu.ynu.demo_app.controller;


/*已弃用，讲customer改为用user*/



/*
import cn.edu.ynu.demo_app.entity.CustomerEntity;
import cn.edu.ynu.demo_app.service.CustomerService;
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

@RestController
@CrossOrigin
@RequestMapping("/public")
@Tag(name = "Customer", description = "用户相关（因为老师写代码的时候写了一个User，所以我把我们的写成了Customer）" )
public class CustomerController {
    @Resource
    CustomerService customerService;

    @PostMapping("/FindCustomerByCustomerId")
    @Operation(summary = "通过customerId查找customer", description = "接收一个Integer的customerId")
    public ResponseEntity<CustomerEntity> findByCustomerId(Integer customer_id){
        try{
            return new ResponseEntity<>(customerService.findByCustomerId(customer_id), HttpStatus.OK);
        } catch (NumberFormatException | NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
*/



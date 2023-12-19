package cn.edu.ynu.demo_app.service;

import cn.edu.ynu.demo_app.entity.CustomerEntity;
import cn.edu.ynu.demo_app.repository.ICustomerRepository;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@CommonsLog
@AllArgsConstructor
@NoArgsConstructor
public class CustomerService {
    @Resource
    private ICustomerRepository iCustomerRepository;

    public CustomerEntity findByCustomerId(Integer customer_id){
        return iCustomerRepository.findByCustomerId(customer_id);
    }
}

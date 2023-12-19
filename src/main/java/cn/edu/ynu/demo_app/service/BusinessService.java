package cn.edu.ynu.demo_app.service;

import cn.edu.ynu.demo_app.entity.BusinessEntity;
import cn.edu.ynu.demo_app.repository.IBusinessRepository;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@CommonsLog
@AllArgsConstructor
@NoArgsConstructor
public class BusinessService {
    @Resource
    private IBusinessRepository iBusinessRepository;

    public List<BusinessEntity> findByBusinessIdIsNotNull(){
        return iBusinessRepository.findByBusinessIdIsNotNull();
    }

    public List<BusinessEntity> findByOrderTypeId(Integer order_type_id){
        return iBusinessRepository.findByOrderTypeId(order_type_id);
    }

    public BusinessEntity findByBusinessId(Integer business_id){
        return iBusinessRepository.findByBusinessId(business_id);
    }
}

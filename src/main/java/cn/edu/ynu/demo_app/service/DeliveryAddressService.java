package cn.edu.ynu.demo_app.service;

import cn.edu.ynu.demo_app.entity.CustomerEntity;
import cn.edu.ynu.demo_app.entity.DeliveryAddressEntity;
import cn.edu.ynu.demo_app.repository.IDeliveryAddressRepository;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@CommonsLog
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressService {

    /*List<DeliveryAddressEntity> findByCustomerEntity_CustomerId(Integer customer_id);
    DeliveryAddressEntity save(Integer daId, String contact_name, Integer contact_sex, String contact_tel, String address, Integer customer_id);
    DeliveryAddressEntity deleteByDaId(Integer da_id);*/

    @Resource
    private IDeliveryAddressRepository iDeliveryAddressRepository;
    CustomerService customerService;

    public List<DeliveryAddressEntity> findByCustomerEntity_CustomerId(Integer customer_id){
        return iDeliveryAddressRepository.findByCustomerEntity_CustomerId(customer_id);
    }

    public DeliveryAddressEntity findByDaId(Integer da_id){
        return iDeliveryAddressRepository.findByDaId(da_id);
    }

    public DeliveryAddressEntity save(Integer da_id, String contact_name, Integer contact_sex, String contact_tel, String address, Integer customer_id){
        CustomerEntity customerEntity = customerService.findByCustomerId(customer_id);
        if(da_id == 0){
            DeliveryAddressEntity newAddress = DeliveryAddressEntity.builder()
                    .contactName(contact_name)
                    .contactSex(contact_sex)
                    .contactTel(contact_tel)
                    .address(address)
                    .customerEntity(customerEntity)
                    .build();
            return iDeliveryAddressRepository.save(newAddress);
        }else{
            DeliveryAddressEntity newAddress = DeliveryAddressEntity.builder()
                    .daId(da_id)
                    .contactName(contact_name)
                    .contactSex(contact_sex)
                    .contactTel(contact_tel)
                    .address(address)
                    .customerEntity(customerEntity)
                    .build();
            return iDeliveryAddressRepository.save(newAddress);
        }
    }

    public DeliveryAddressEntity deleteByDaId(Integer da_id){
        return iDeliveryAddressRepository.deleteByDaId(da_id);
    }
}

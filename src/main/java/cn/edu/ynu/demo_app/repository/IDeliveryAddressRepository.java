package cn.edu.ynu.demo_app.repository;

import cn.edu.ynu.demo_app.entity.DeliveryAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDeliveryAddressRepository extends JpaRepository<DeliveryAddressEntity, Integer>{
    List<DeliveryAddressEntity> findByUserEntityCode(String user_code);
    DeliveryAddressEntity save(DeliveryAddressEntity deliveryAddressEntity);
    DeliveryAddressEntity deleteByDaId(Integer da_id);
    DeliveryAddressEntity findByDaId(Integer da_id);
}

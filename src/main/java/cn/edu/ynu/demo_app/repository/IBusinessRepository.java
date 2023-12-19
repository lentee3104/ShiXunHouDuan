package cn.edu.ynu.demo_app.repository;

import cn.edu.ynu.demo_app.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBusinessRepository extends JpaRepository<BusinessEntity, Integer> {
    List<BusinessEntity> findByBusinessIdIsNotNull();
    List<BusinessEntity> findByOrderTypeId(Integer order_type_id);
    BusinessEntity findByBusinessId(Integer business_id);
}

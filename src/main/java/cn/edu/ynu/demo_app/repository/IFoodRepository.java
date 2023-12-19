package cn.edu.ynu.demo_app.repository;

import cn.edu.ynu.demo_app.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFoodRepository extends JpaRepository<FoodEntity, Integer> {
    List<FoodEntity> findByBusinessEntityBusinessId(Integer business_id);
    FoodEntity findByFoodId(Integer food_if);
}

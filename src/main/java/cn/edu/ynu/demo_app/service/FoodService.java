package cn.edu.ynu.demo_app.service;

import cn.edu.ynu.demo_app.entity.FoodEntity;
import cn.edu.ynu.demo_app.repository.IFoodRepository;
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
public class FoodService {
    @Resource
    private final IFoodRepository iFoodRepository;

    public FoodService(IFoodRepository iFoodRepository) {
        this.iFoodRepository = iFoodRepository;
    }

    public List<FoodEntity> findByBusinessEntityBusinessId(Integer business_id){
        return iFoodRepository.findByBusinessEntityBusinessId(business_id);
    }

    public FoodEntity findByFoodId(Integer food_if){
        return iFoodRepository.findByFoodId(food_if);
    }
}

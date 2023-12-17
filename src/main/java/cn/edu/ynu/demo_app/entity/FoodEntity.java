package cn.edu.ynu.demo_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Food")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer foodId;
    public String foodName;
    public String foodExplain;
    public String foodImg;
    public Double foodPrice;
    public String remarks;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "business_id",/*这里和数据库表的列名对应*/
            referencedColumnName = "businessId"/*这里和entity的Business类里面的属性对应*/
    )
    private BusinessEntity businessEntity;
}

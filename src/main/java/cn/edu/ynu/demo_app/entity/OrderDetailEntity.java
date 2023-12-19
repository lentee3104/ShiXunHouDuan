package cn.edu.ynu.demo_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OrderDetail")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer odId;
    public Integer quantity;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "order_id",/*这里和数据库表的列名对应*/
            referencedColumnName = "orderId"/*这里和entity的Business类里面的属性对应*/
    )
    private OrderTableEntity orderTableEntity;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "food_id",/*这里和数据库表的列名对应*/
            referencedColumnName = "foodId"/*这里和entity的Business类里面的属性对应*/
    )
    private FoodEntity foodEntity;
}

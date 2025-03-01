package cn.edu.ynu.demo_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OrderTable")
public class OrderTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer orderId;
    public LocalDateTime orderDate = LocalDateTime.now();
    public Double orderTotal;
    public Integer orderState = 0;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "user_code",/*这里是当前表叫啥*/
            referencedColumnName = "code"/*这里是对应的外键的列名*/
    )
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "business_id",/*这里和数据库表的列名对应*/
            referencedColumnName = "businessId"/*这里和entity的Business类里面的属性对应*/
    )
    private BusinessEntity businessEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "da_id",/*这里和数据库表的列名对应*/
            referencedColumnName = "daId"/*这里和entity的Business类里面的属性对应*/
    )
    private DeliveryAddressEntity deliveryAddressEntity;
}

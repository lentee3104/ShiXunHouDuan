package cn.edu.ynu.demo_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DeliveryAddress")
public class DeliveryAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer daId;
    public String contactName;
    public Integer contactSex;
    public String contactTel;
    public String address;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "customer_id",/*这里和数据库表的列名对应*/
            referencedColumnName = "customerId"/*这里和entity的Business类里面的属性对应*/
    )
    private CustomerEntity customerEntity;
}

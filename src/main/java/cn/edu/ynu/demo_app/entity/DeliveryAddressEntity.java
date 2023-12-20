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
            name = "user_code",/*当前表的字段*/
            referencedColumnName = "code"/*引用表对应的字段，如果不注明，默认就是引用表的主键*/
    )
    private UserEntity userEntity;
}

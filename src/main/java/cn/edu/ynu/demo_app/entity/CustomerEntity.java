package cn.edu.ynu.demo_app.entity;


/*已弃用，改为使用user*/


/*

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customer",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {
                    "customer_name"
            })
        }
)
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer customerId;
    public String password;
    @Column(name="customer_name")
    public String customerName;
    public Integer customerSex = 1;
    public String customerImg;
    public Integer delTag = 1;
}
*/

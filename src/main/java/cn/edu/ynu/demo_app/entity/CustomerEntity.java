package cn.edu.ynu.demo_app.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer customerId;
    public String password;
    public String customerName;
    public Integer customerSex = 1;
    public String customerImg;
    public Integer delTag = 1;
}

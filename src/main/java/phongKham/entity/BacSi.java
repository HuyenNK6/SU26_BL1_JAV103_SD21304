package phongKham.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BacSi")
public class BacSi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ten")
    private String ten;
    @Column(name = "diaChi")
    private String diaChi;
    @Column(name = "luong")
    private BigDecimal luong;
    @Column(name = "idPhongKham")
    private Integer idPhongKham;//chứa id foreign key -> phòng khám
}

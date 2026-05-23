package phongKham.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor//constructor ko tham số
@AllArgsConstructor//constructor full tham số
@Getter
@Setter
@Entity//đánh dấu class này là 1 bảng trong database
@Table(name = "PhongKham")//Map class PhongKham -> bảng PhongKham trong database
public class PhongKham {
    @Id//đánh dấu đây là khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)//tự động tăng ID, ko cần insert
    private Integer id;
    @Column(name = "ten")//Column -> tên cột trong database -> Map field "ten" <-> cột tên
    private String ten;
    @Column(name = "soNha")
    private String soNha;
}

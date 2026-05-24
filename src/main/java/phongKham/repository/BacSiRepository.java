package phongKham.repository;

import org.hibernate.Session;
import phongKham.entity.BacSi;
import phongKham.util.HibernateConfig;

import java.util.List;

public class BacSiRepository {
    //tạo đối tượng phiên làm việc: Java <-> Database
    //Session giống như kết nối giữa Java và database; Mỗi lần muốn truy vấn DB thì cần session
    private Session session;
    //click chuột phải -> Generate -> select none
    public BacSiRepository() {
        session = HibernateConfig.getFACTORY().openSession();//mở phiên làm việc
    }
    /*
        - HQL (Hibernate Query Language) là ngôn ngữ truy vấn của Hibernate dùng để làm việc với dữ liệu
        thông qua các class Java (Entity) thay vì thao tác trực tiếp với bảng trong database
            + SQL → làm việc với bảng (table), cột (column)
            + HQL → làm việc với class (entity), thuộc tính (field)
     */
    //java.util
    public List <BacSi> getAll(){
        return session.createQuery("FROM BacSi").list();
    }

    public BacSi getOne(Integer id){
        return session.find(BacSi.class, id);
    }
    public void add(BacSi bacSi){
        //dùng try-catch để bắt lỗi khi làm việc vs database
        //vd: lỗi kết nối, lỗi sql, sai dữ liệu...
        try {
            session.getTransaction().begin();//bắt đầu
            session.save(bacSi);//insert vào database
            session.getTransaction().commit();//lưu thao tác
        }catch (Exception e){
            session.getTransaction().rollback();//hủy toàn bộ thao tác nếu lỗi
            //vd: nếu insert lỗi thì rollback về trạng thái ban đầu
            e.printStackTrace();//in chi tiết lỗi
        }
    }
    public static void main(String[] args) {
        //đi test chức năng
        System.out.println(new BacSiRepository().getAll());
    }
}

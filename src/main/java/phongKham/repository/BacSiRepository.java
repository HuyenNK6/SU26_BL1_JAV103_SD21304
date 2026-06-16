package phongKham.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
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
    public List<BacSi> getAll() {
        return session.createQuery("FROM BacSi").list();
    }

    public BacSi getOne(Integer id) {
        return session.find(BacSi.class, id);
    }

    public void add(BacSi bacSi) {
        //dùng try-catch để bắt lỗi khi làm việc vs database
        //vd: lỗi kết nối, lỗi sql, sai dữ liệu...
        try {
            session.getTransaction().begin();//bắt đầu
            session.save(bacSi);//insert vào database
            session.getTransaction().commit();//lưu thao tác
        } catch (Exception e) {
            session.getTransaction().rollback();//hủy toàn bộ thao tác nếu lỗi
            //vd: nếu insert lỗi thì rollback về trạng thái ban đầu
            e.printStackTrace();//in chi tiết lỗi
        }
    }

    public void update(BacSi bacSi) {
        //dùng try-catch để bắt lỗi khi làm việc vs database
        //vd: lỗi kết nối, lỗi sql, sai dữ liệu...
        try {
            session.getTransaction().begin();//bắt đầu
            session.merge(bacSi);//update vào database
            session.getTransaction().commit();//lưu thao tác
        } catch (Exception e) {
            session.getTransaction().rollback();//hủy toàn bộ thao tác nếu lỗi
            //vd: nếu insert lỗi thì rollback về trạng thái ban đầu
            e.printStackTrace();//in chi tiết lỗi
        }
    }

    public void delete(Integer id) {
        //dùng try-catch để bắt lỗi khi làm việc vs database
        //vd: lỗi kết nối, lỗi sql, sai dữ liệu...
        try {
            session.getTransaction().begin();//bắt đầu
            session.delete(this.getOne(id));//xóa khỏi database
            session.getTransaction().commit();//lưu thao tác
        } catch (Exception e) {
            session.getTransaction().rollback();//hủy toàn bộ thao tác nếu lỗi
            //vd: nếu insert lỗi thì rollback về trạng thái ban đầu
            e.printStackTrace();//in chi tiết lỗi
        }
    }

    //10 phần tử: pageNumber = 1, pageSize = 3
    //trang 1 : (1-1)* 3=0 => 0 1 2
    //trang 2 : (2-1)* 3=3 => 3 4 5
    //trang 3 : (3-1)* 3=6 => 6 7 8
    //trang 4 : (4-1)* 3=9 => 9
    public List<BacSi> paging(Integer pageNumber, Integer pageSize) {
        //import org.hibernate.query.Query;
        Query query = session.createQuery("FROM BacSi ");
        //xác định điểm đầu tiên lấy kết quả
        query.setFirstResult((pageNumber - 1) * pageSize);
        //giới hạn số lượng bản ghi được lấy ra
        query.setMaxResults(pageSize);
        return query.list();
    }

    public static void main(String[] args) {
        //đi test chức năng
        System.out.println(new BacSiRepository().getAll());
    }
}

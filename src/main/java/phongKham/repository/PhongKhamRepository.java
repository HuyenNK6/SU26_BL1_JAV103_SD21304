package phongKham.repository;

import org.hibernate.Session;
import phongKham.entity.PhongKham;
import phongKham.util.HibernateConfig;

import java.util.List;

public class PhongKhamRepository {
    private Session session;

    public PhongKhamRepository() {
        session= HibernateConfig.getFACTORY().openSession();
    }
    public List <PhongKham> getAll(){
        return session.createQuery("FROM PhongKham").list();
    }
    public PhongKham getOne(Integer id){
        return session.find(PhongKham.class, id);
    }
}

package service;

import model.Admin;
import model.Business;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Created by Quang Linh on 04 Aug 2020
 */
@Transactional
@Service
public class AdminService {

    @Autowired
    private SessionFactory sessionFactory;

    public void  setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //save admin
    public void saveAdmin(Admin admin) {
        System.out.println(admin.getBusiness());
        if (admin.getBusiness()!=null){
            int business_id = admin.getBusiness().getId();
            System.out.println((business_id));
            Query query = sessionFactory.getCurrentSession().createQuery("from Business where id= :id");
            query.setInteger("id",business_id);
            Business business = (Business) query.uniqueResult();
            admin.setBusiness(business);
        }
        sessionFactory.getCurrentSession().save(admin);
    }


    //get admin
    public Admin getAdmin(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Admin where id=:id");
        query.setInteger("id", id);
        return (Admin) query.uniqueResult();
    }


    //get list admin
    public List<Admin> getAllAdmin(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Admin");
        return query.list();
    }

    //find admin by name
    public List<Admin> findAdmin(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from Admin where name=:name");
        query.setString("name", "%"+name+"%");
        return query.list();
    }

    //update admin
    public void updateAdmin(Admin admin) {
        sessionFactory.getCurrentSession().update(admin);
    }

    //delete admin
    public void deleteAdmin(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Admin where id:id");
        query.setInteger("id",id);
        Admin admin = (Admin) query.uniqueResult();
        sessionFactory.getCurrentSession().delete(admin);
    }

}






















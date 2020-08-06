package service;


import model.Service;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class ServiceService {

    @Autowired
    private SessionFactory sessionFactory;

    // get all service
    public List<Service> getAllServices(){
        Query query = sessionFactory.getCurrentSession().createQuery("From Service");
        return query.list();
    }

    // ad service
    public void addService(Service service){
        this.sessionFactory.getCurrentSession().save(service);

    }

    // update service
    public void updateService(Service service){
        sessionFactory.getCurrentSession().update(service);
    }

    // delete service
    public void deleteService(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("From Service where id = :id");
        query.setInteger("id", id);
        Service service = (Service) query.uniqueResult();
        sessionFactory.getCurrentSession().delete(service);
    }


}
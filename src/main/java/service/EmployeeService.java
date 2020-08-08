package service;


import model.Customer;
import model.Employee;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private SessionFactory sessionFactory;

    // get all employees
    public List<Employee> getAllEmployees(){
        Query query = sessionFactory.getCurrentSession().createQuery("From Employee");
        return query.list();
    }

    // add employee
    public void addEmployee(Employee employee){
        sessionFactory.getCurrentSession().save(employee);
    }

    // update employee
    public void updateEmployee(Employee employee){
        sessionFactory.getCurrentSession().update(employee);
    }

    // delete employee
    public void deleteEmployee(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("From Employee where id = :id");
        query.setInteger("id", id);
        Employee employee = (Employee) query.uniqueResult();
        sessionFactory.getCurrentSession().delete(employee);
    }


}

package repositories;

import models.Employee;

import java.util.List;

public class EmployeeRepository extends GenericRepositoryImplementation<Employee,Long> implements GenericRepository<Employee,Long>{

    @Override
    public Employee findById(Long id) {
        try(var session = sessionFactory.openSession()){
            return session.find(Employee.class,id);
        }
    }

    @Override
    public List<Employee> findAll() {
        try(var session = sessionFactory.openSession()){
            return session
                    .createQuery("SELECT e FROM models.Employee e",Employee.class)
                    .list();
        }
    }

    public void truncate(){
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE Employee CASCADE ", Employee.class)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public void deleteById(Long id) {
        try(var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try{
                var employee = session.createQuery("FROM models.Employee e WHERE e.id = :id")
                        .setParameter("id",id)
                        .getSingleResult();
                session.delete(employee);
                transaction.commit();
            }catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    public Employee findByUserName(String userName) {
        /*try (var session = sessionFactory.openSession()) {
            Employee employee = new Employee(userName);
            return session.find(Employee.class, userName);
        }*/
        try(var session = sessionFactory.openSession()){
                return (Employee) session.createQuery("SELECT e FROM models.Employee e WHERE e.userName = :userName")
                        .setParameter("userName",userName)
                        .getSingleResult();
        }
    }
}

package com.example.DAO;

import com.example.model.Employee;
import com.example.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDAO {

    // CREATE
    public void saveEmployee(Employee employee) {

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil
                             .getSessionFactory()
                             .openSession()) {

            transaction = session.beginTransaction();

            session.persist(employee);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }


    // READ ALL
    public List<Employee> getAllEmployees() {

        try (Session session =
                     HibernateUtil
                             .getSessionFactory()
                             .openSession()) {

            return session
                    .createQuery(
                            "FROM Employee",
                            Employee.class
                    )
                    .getResultList();
        }
    }


    // READ ONE
    public Employee getEmployeeById(int id) {

        try (Session session =
                     HibernateUtil
                             .getSessionFactory()
                             .openSession()) {

            return session.get(Employee.class, id);
        }
    }


    // UPDATE
    public void updateEmployee(Employee employee) {

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil
                             .getSessionFactory()
                             .openSession()) {

            transaction = session.beginTransaction();

            session.merge(employee);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }


    // DELETE
    public void deleteEmployee(int id) {

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil
                             .getSessionFactory()
                             .openSession()) {

            transaction = session.beginTransaction();

            Employee employee =
                    session.get(Employee.class, id);

            if (employee != null) {

                session.remove(employee);
            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}
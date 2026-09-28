package com.example;

import com.example.DAO.EmployeeDAO;
import com.example.model.Employee;

import java.util.List;

public class TestEmployeeDAO {

    public static void main(String[] args) {

        EmployeeDAO employeeDAO =
                new EmployeeDAO();


        // =========================
        // CREATE
        // =========================

        Employee newEmployee =
                new Employee(
                        "Arjun",
                        "arjun@gmail.com",
                        "Development",
                        70000
                );

        employeeDAO.saveEmployee(newEmployee);

        System.out.println(
                "Employee created!"
        );


        // =========================
        // READ ALL
        // =========================

        List<Employee> employees =
                employeeDAO.getAllEmployees();

        System.out.println(
                "\n----- ALL EMPLOYEES -----"
        );

        for (Employee employee : employees) {

            System.out.println(
                    employee.getId()
                            + " | "
                            + employee.getName()
                            + " | "
                            + employee.getEmail()
                            + " | "
                            + employee.getDepartment()
                            + " | "
                            + employee.getSalary()
            );
        }


        // =========================
        // READ ONE
        // =========================

        Employee employee =
                employeeDAO.getEmployeeById(1);

        System.out.println(
                "\n----- EMPLOYEE ID 1 -----"
        );

        if (employee != null) {

            System.out.println(
                    employee.getId()
                            + " | "
                            + employee.getName()
                            + " | "
                            + employee.getEmail()
                            + " | "
                            + employee.getDepartment()
                            + " | "
                            + employee.getSalary()
            );
        }


        // =========================
        // UPDATE
        // =========================

        Employee employeeToUpdate =
                employeeDAO.getEmployeeById(1);

        if (employeeToUpdate != null) {

            employeeToUpdate.setSalary(80000);

            employeeToUpdate.setDepartment(
                    "Senior Development"
            );

            employeeDAO.updateEmployee(
                    employeeToUpdate
            );

            System.out.println(
                    "\nEmployee updated!"
            );
        }


        // =========================
        // DELETE
        // =========================

        employeeDAO.deleteEmployee(2);

        System.out.println(
                "\nEmployee deleted!"
        );
    }
}
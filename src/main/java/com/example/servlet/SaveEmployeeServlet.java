package com.example.servlet;

import com.example.DAO.EmployeeDAO;
import com.example.model.Employee;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/save-employee")
public class SaveEmployeeServlet extends HttpServlet {

    private EmployeeDAO employeeDAO;

    @Override
    public void init() {

        employeeDAO = new EmployeeDAO();

        System.out.println("SaveEmployeeServlet initialized");
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        System.out.println("========== SAVE EMPLOYEE ==========");

        // Get values from HTML form

        String idParameter =
                request.getParameter("id");

        String name =
                request.getParameter("name");

        String email =
                request.getParameter("email");

        String department =
                request.getParameter("department");

        String salaryParameter =
                request.getParameter("salary");


        // Print received values

        System.out.println("ID         : " + idParameter);
        System.out.println("Name       : " + name);
        System.out.println("Email      : " + email);
        System.out.println("Department : " + department);
        System.out.println("Salary     : " + salaryParameter);


        // Convert ID

        int id = 0;

        if (idParameter != null &&
                !idParameter.isBlank()) {

            id = Integer.parseInt(idParameter);
        }


        // Convert salary

        double salary = 0;

        if (salaryParameter != null &&
                !salaryParameter.isBlank()) {

            salary = Double.parseDouble(salaryParameter);
        }


        // Create Employee object

        Employee employee =
                new Employee();

        employee.setId(id);
        employee.setName(name);
        employee.setEmail(email);
        employee.setDepartment(department);
        employee.setSalary(salary);


        // CREATE

        if (id == 0) {

            System.out.println(
                    "Creating new employee..."
            );

            employeeDAO.saveEmployee(employee);

        }

        // UPDATE

        else {

            System.out.println(
                    "Updating employee..."
            );

            employeeDAO.updateEmployee(employee);
        }


        // Go back to employee list

        response.sendRedirect(
                request.getContextPath()
                        + "/employees"
        );
    }
}
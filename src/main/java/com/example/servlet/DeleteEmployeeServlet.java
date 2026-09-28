package com.example.servlet;

import com.example.DAO.EmployeeDAO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/delete-employee")
public class DeleteEmployeeServlet extends HttpServlet {

    private EmployeeDAO employeeDAO;

    @Override
    public void init() {

        employeeDAO =
                new EmployeeDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        int id =
                Integer.parseInt(
                        request.getParameter("id")
                );

        employeeDAO.deleteEmployee(id);

        response.sendRedirect(
                request.getContextPath()
                        + "/employees"
        );
    }
}
package com.example.servlet;

import com.example.DAO.EmployeeDAO;
import com.example.model.Employee;
import com.example.util.ThymeleafConfig;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/edit-employee")
public class EditEmployeeServlet extends HttpServlet {

    private EmployeeDAO employeeDAO;

    private TemplateEngine templateEngine;

    @Override
    public void init() {

        employeeDAO =
                new EmployeeDAO();

        templateEngine =
                ThymeleafConfig
                        .createTemplateEngine();
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

        Employee employee =
                employeeDAO.getEmployeeById(id);


        if (employee == null) {

            response.sendError(
                    HttpServletResponse.SC_NOT_FOUND,
                    "Employee not found"
            );

            return;
        }


        IWebExchange exchange =
                JakartaServletWebApplication
                        .buildApplication(
                                getServletContext()
                        )
                        .buildExchange(
                                request,
                                response
                        );


        WebContext context =
                new WebContext(
                        exchange,
                        request.getLocale()
                );


        context.setVariable(
                "employee",
                employee
        );


        templateEngine.process(
                "employee-form",
                context,
                response.getWriter()
        );
    }
}
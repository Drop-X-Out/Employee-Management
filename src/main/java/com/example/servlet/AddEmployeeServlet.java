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

@WebServlet("/add-employee")
public class AddEmployeeServlet extends HttpServlet {

    private EmployeeDAO employeeDAO;

    private TemplateEngine templateEngine;

    @Override
    public void init() {

        employeeDAO = new EmployeeDAO();

        templateEngine =
                ThymeleafConfig
                        .createTemplateEngine();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        Employee employee =
                new Employee();

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
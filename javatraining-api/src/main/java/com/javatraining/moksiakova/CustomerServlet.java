package com.javatraining.moksiakova;

import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.services.CustomerService;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomerServlet extends HttpServlet {

    private final CustomerService service = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomResponse resp = service.createCustomer(request.getParameterMap());
        response.setStatus(resp.getCode());
        response.setContentType("application/json");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(resp.getCode());
        out.println("<br>");
        out.println(resp.getMessage());
        //Map requestMap = request.getParameterMap();
        //if (requestMap.isEmpty()) {
            // all customers
        //} else if (requestMap.containsKey("id")) {
            //
        //}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CustomResponse resp = service.createCustomer(request.getParameterMap());
        response.setStatus(resp.getCode());
        response.setContentType("application/json");
    }
}

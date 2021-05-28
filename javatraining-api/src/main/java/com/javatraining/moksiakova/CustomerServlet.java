package com.javatraining.moksiakova;

import com.google.gson.Gson;
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

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        Map requestMap = request.getParameterMap();
        CustomResponse<Customer> rrr = service.findCustomer(1);
        if (rrr.getCode() == 200) {
            this.sendResponse(gson.toJson(rrr.getJsonObject()), response);
        }
        else {
            this.sendResponse(gson.toJson(rrr), response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        CustomResponse resp = service.createCustomer(request.getParameterMap());
        response.setStatus(resp.getCode());
        response.setContentType("application/json");
    }

    private void sendResponse(String objectString, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(objectString);
        out.flush();
    }
}

package com.javatraining.moksiakova;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Slf4j
public class CustomerServlet extends HttpServlet {

    private final CustomerService service = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        CustomResponse customResponse;
        int idCustomer = 0;
        try{
            idCustomer = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {}

        if (idCustomer > 0) {
            customResponse = service.findCustomer(idCustomer);
        } else {
            customResponse = service.findAll();
        }
        this.sendResponse(customResponse, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer = new Gson().fromJson(request.getReader(), Customer.class);
        CustomResponse<Customer> customResponse = service.createCustomer(customer);
        this.sendResponse(customResponse, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer = new Gson().fromJson(request.getReader(), Customer.class);
        CustomResponse<Customer> customResponse = service.updateCustomer(customer);
        this.sendResponse(customResponse, response);
    }

    private void sendResponse(CustomResponse customResponse, HttpServletResponse response)
            throws IOException
    {
        GsonBuilder builder = new GsonBuilder().disableHtmlEscaping();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        String objectString;
        if (customResponse.getCode() == 200) {
            objectString = gson.toJson(customResponse.getEntity());
        } else {
            objectString = gson.toJson(customResponse);
        }
        PrintWriter out = response.getWriter();
        response.setStatus(customResponse.getCode());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(objectString);
        out.flush();
    }
}

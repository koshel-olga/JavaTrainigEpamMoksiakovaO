package com.javatraining.moksiakova;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.javatraining.moksiakova.domain.entity.Customer;
import com.javatraining.moksiakova.services.CustomerService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class CustomerServlet extends CustomHttpServlet {

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

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            Customer customer = new Gson().fromJson(request.getReader(), Customer.class);
            CustomResponse<Customer> customResponse = service.deleteCustomer(customer.getCustomerId());
            this.sendResponse(customResponse, response);
        } catch (Exception e) {
            CustomResponse<?> customResponse = new CustomResponse<>(400,e.getMessage(), null);
            this.sendResponse(customResponse, response);
        }
    }
}

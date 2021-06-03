package com.javatraining.moksiakova.servlets;

import com.google.gson.Gson;
import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.domain.entity.Supplier;
import com.javatraining.moksiakova.service.SupplierServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplierServlet extends CustomHttpServlet {

    private final SupplierServiceImpl service;

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
            customResponse = service.findSupplier(idCustomer);
        } else {
            customResponse = service.findAll();
        }
        this.sendResponse(customResponse, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Supplier supplier = new Gson().fromJson(request.getReader(), Supplier.class);
        CustomResponse<Supplier> customResponse = service.createSupplier(supplier);
        this.sendResponse(customResponse, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Supplier supplier = new Gson().fromJson(request.getReader(), Supplier.class);
        CustomResponse<Supplier> customResponse = service.updateSupplier(supplier);
        this.sendResponse(customResponse, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            Supplier supplier = new Gson().fromJson(request.getReader(), Supplier.class);
            CustomResponse<Supplier> customResponse = service.deleteSupplier(supplier.getSupplierId());
            this.sendResponse(customResponse, response);
        } catch (Exception e) {
            CustomResponse<?> customResponse = new CustomResponse<>(400,e.getMessage(), null);
            this.sendResponse(customResponse, response);
        }
    }
}

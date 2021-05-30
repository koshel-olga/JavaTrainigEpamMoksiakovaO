package com.javatraining.moksiakova.servlets;

import com.google.gson.Gson;
import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.domain.entity.Product;
import com.javatraining.moksiakova.services.ProductService;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class ProductServlet extends CustomHttpServlet {
    private final ProductService service = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        CustomResponse<?> customResponse;
        int id = 0;
        try{
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {}

        if (id > 0) {
            customResponse = service.findProduct(id);
        } else {
            customResponse = service.findAll();
        }
        this.sendResponse(customResponse, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Product product = new Gson().fromJson(request.getReader(), Product.class);
            CustomResponse<Product> customResponse = service.createProduct(product);
            this.sendResponse(customResponse, response);
        } catch (Exception e) {
            CustomResponse<?> customResponse = new CustomResponse<>(400,e.getMessage(), null);
            this.sendResponse(customResponse, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Product product = new Gson().fromJson(request.getReader(), Product.class);
            CustomResponse<Product> customResponse = service.updateProduct(product);
            this.sendResponse(customResponse, response);
        } catch (Exception e) {
            CustomResponse<?> customResponse = new CustomResponse<>(400,e.getMessage(), null);
            this.sendResponse(customResponse, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            Product product = new Gson().fromJson(request.getReader(), Product.class);
            CustomResponse<Product> customResponse = service.deleteProduct(product.getProductId());
            this.sendResponse(customResponse, response);
        } catch (Exception e) {
            CustomResponse<?> customResponse = new CustomResponse<>(400,e.getMessage(), null);
            this.sendResponse(customResponse, response);
        }

    }
}

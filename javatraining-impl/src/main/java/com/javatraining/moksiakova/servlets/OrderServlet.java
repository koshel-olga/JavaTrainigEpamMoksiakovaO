package com.javatraining.moksiakova.servlets;

import com.google.gson.Gson;
import com.javatraining.moksiakova.CustomResponse;
import com.javatraining.moksiakova.domain.entity.Order;
import com.javatraining.moksiakova.payload.OrderPayload;
import com.javatraining.moksiakova.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OrderServlet extends CustomHttpServlet {

    private final OrderServiceImpl service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        CustomResponse<?> customResponse;
        int idOrder = 0;
        try{
            idOrder = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {}

        if (idOrder > 0) {
            customResponse = service.findOrder(idOrder);
        } else {
            customResponse = service.findAll();
        }
        this.sendResponse(customResponse, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            OrderPayload order = new Gson().fromJson(request.getReader(), OrderPayload.class);
            CustomResponse<Order> customResponse = service.createOrder(order);
            this.sendResponse(customResponse, response);
        } catch (Exception e) {
            CustomResponse<?> customResponse = new CustomResponse<>(400,e.getMessage(), null);
            this.sendResponse(customResponse, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            OrderPayload order = new Gson().fromJson(request.getReader(), OrderPayload.class);
            CustomResponse<Order> customResponse = service.updateOrder(order);
            this.sendResponse(customResponse, response);
        } catch (Exception e) {
            CustomResponse<?> customResponse = new CustomResponse<>(400,e.getMessage(), null);
            this.sendResponse(customResponse, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            OrderPayload order = new Gson().fromJson(request.getReader(), OrderPayload.class);
            CustomResponse<Order> customResponse = service.deleteOrder(order.getOrderId());
            this.sendResponse(customResponse, response);
        } catch (Exception e) {
            CustomResponse<?> customResponse = new CustomResponse<>(400,e.getMessage(), null);
            this.sendResponse(customResponse, response);
        }
    }
}

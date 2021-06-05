package com.javatraining.moksiakova.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.javatraining.moksiakova.CustomResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomHttpServlet extends HttpServlet {

    public void sendResponse(CustomResponse<?> customResponse, HttpServletResponse response)
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

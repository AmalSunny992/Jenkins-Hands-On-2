package com.example.weatherapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/weather")
public class WeatherServlet extends HttpServlet {

    private static final String API_KEY = "your_api_key";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");

        if (city == null || city.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "City is required");
            return;
        }

        String apiUrl = String.format(API_URL, city, API_KEY);

        Response response = Request.Get(apiUrl).execute();
        String jsonResponse = response.returnContent().asString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);
        JsonNode weatherNode = rootNode.path("weather").get(0).path("description");
        JsonNode tempNode = rootNode.path("main").path("temp");

        req.setAttribute("weather", weatherNode.asText());
        req.setAttribute("temperature", tempNode.asDouble() - 273.15); // Convert from Kelvin to Celsius
        req.setAttribute("city", city);

        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }
}
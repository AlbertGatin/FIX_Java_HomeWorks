package ru.ivmiit.homework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ArrayList<Product> products = new ArrayList<Product>();
//        products.add(new Product("Компуктер", 25000.00));
//        products.add(new Product("Телефун", 5000.00));
//        products.add(new Product("Айрподс", 1000.00));
//        products.add(new Product("Нубук", 30000.00));
//        products.add(new Product("Принтер", 10000.00));
//
//        PrintWriter writer = resp.getWriter();
//        writer.write("<h1>Список товаров</h1>");
//        writer.write("<table>");
//        for (Product product:products) {
//            writer.write("<tr><td>" + product.getName() + "</td><td>" + product.getCost() + "</td></tr>");
//        }
//        writer.write("</table>");
        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}

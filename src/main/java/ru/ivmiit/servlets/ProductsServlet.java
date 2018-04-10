package ru.ivmiit.servlets;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.dao.ProductsDao;
import ru.ivmiit.dao.ProductsDaoJdbcImpl;
import ru.ivmiit.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.function.ToDoubleFunction;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    private ProductsDao productsDao;
    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();
        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setUrl(dbUrl);
            dataSource.setDriverClassName(driverClassName);

            productsDao = new ProductsDaoJdbcImpl(dataSource);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = null;
        products = productsDao.findAll();
        req.setAttribute("productsFromServer", products);
        req.getServletContext().getRequestDispatcher("/jsp/products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product(req.getParameter("name"), req.getParameter("desc"), Double.parseDouble(req.getParameter("cost")));
        productsDao.add(product);
        List<Product> products = null;
        products = productsDao.findAll();
        doGet(req, resp);
    }
}

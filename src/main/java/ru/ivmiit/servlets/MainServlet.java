package ru.ivmiit.servlets;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.dao.ProductsDao;
import ru.ivmiit.dao.ProductsDaoJdbcImpl;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.dao.UsersDaoJdbcImpl;
import ru.ivmiit.models.Product;
import ru.ivmiit.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private UsersDao usersDao;
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

            usersDao = new UsersDaoJdbcImpl(dataSource);
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
        req.getServletContext().getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("login"), req.getParameter("password"));
        String responseMessage = "";
        User test = usersDao.find(user);
        if (test == null) {
            resp.sendRedirect(req.getContextPath() + "/main");
        } else if (test != null && BCrypt.checkpw(test.getPassword(), user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user.getLogin());
            Cookie userCookie = new Cookie("user", user.getLogin());
            resp.addCookie(userCookie);
            req.getServletContext().getRequestDispatcher("/products").forward(req, resp);

        }
            else{
            resp.sendRedirect(req.getContextPath() + "/main");
        }
    }
}

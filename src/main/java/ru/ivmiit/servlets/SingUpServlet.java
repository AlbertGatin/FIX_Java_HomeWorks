package ru.ivmiit.servlets;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ivmiit.dao.ProductsDaoJdbcImpl;
import ru.ivmiit.dao.UsersDao;
import ru.ivmiit.dao.UsersDaoJdbcImpl;
import ru.ivmiit.models.Product;
import ru.ivmiit.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@WebServlet("/signUp")
public class SingUpServlet extends HttpServlet {

    UsersDao usersDao;
    boolean succsessfullSignUp = false;

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
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("login"), req.getParameter("password"), req.getParameter("first-name"), req.getParameter("last-name"), req.getParameter("mobile"), req.getParameter("email"));
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        if (usersDao.find(user) == null) {
            resp.sendRedirect(req.getContextPath() + "/signUp");
        } else {
            usersDao.add(user);
            req.getServletContext().getRequestDispatcher("/main").forward(req, resp);
        }
    }
}

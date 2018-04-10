package ru.ivmiit.dao;

import ru.ivmiit.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsDaoJdbcImpl implements ProductsDao {

    //language=SQL
    private final String SQL_INSERT_INTO =
            "INSERT INTO product (product_name,description,cost) VALUES (?, ?, ?)";

    //language=SQL
    private final String SQL_SELECT_ALL =
            "SELECT * FROM product";

    private Connection connection;

    public ProductsDaoJdbcImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void add(Product model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO);
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setDouble(3, model.getCost());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            List<Product> products = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("product_name");
                String description = resultSet.getString("description");
                Double cost = resultSet.getDouble("cost");

                Product product = new Product(id, name, description,cost);

                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Product find(Long id) {
        return null;
    }

    @Override
    public void save(Product model) {

    }

    @Override
    public void update(Product model) {

    }

    @Override
    public void delete(Product model) {

    }

    @Override
    public Product find(Product model) {
        return null;
    }
}

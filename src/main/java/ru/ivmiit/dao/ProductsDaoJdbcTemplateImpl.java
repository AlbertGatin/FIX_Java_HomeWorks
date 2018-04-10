package ru.ivmiit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.ivmiit.models.Product;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.*;

public class ProductsDaoJdbcTemplateImpl implements ProductsDao {

    private JdbcTemplate template;

    private Map<Integer, Product> usersMap = new HashMap<>();

    //language=SQL
    private final String SQL_INSERT_INTO =
            "INSERT INTO product (product_name,description,cost) VALUES (?, ?, ?)";

    //language=SQL
    private final String SQL_SELECT_ALL =
            "SELECT * FROM product";

    public ProductsDaoJdbcTemplateImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }


    @Override
    public void add(Product model) {

    }

    @Override
    public List<Product> findAll() {
        return null;
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

package ru.ivmiit.dao;

import ru.ivmiit.models.Product;

import java.util.List;

public interface ProductsDao extends CrudDao<Product> {

    void add(Product model);

    List<Product> findAll();

}

package ru.ivmiit.dao;

import ru.ivmiit.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersDao extends CrudDao<User> {
    void add(User model);

    User find(User model);

    void update(User model);

    void delete(User model);
}

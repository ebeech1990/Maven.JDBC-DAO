package daos;

import models.User;

import java.util.Set;

public interface UserDAO {
    User findById(Integer id);
    Set<User> findAll();
    User getUserByNameAndPassword(String user, String pass);
    boolean update(User dto);
    boolean create(User dto);
    boolean delete(User dto);
}

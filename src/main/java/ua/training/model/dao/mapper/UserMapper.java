package ua.training.model.dao.mapper;

import ua.training.model.entity.User;
import ua.training.model.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {

        return new User.UserBuilder()
        .setId(rs.getInt("user_id"))
        .setName(rs.getString("name"))
        .setName_ukr(rs.getString("name_ukr"))
        .setLastName(rs.getString("last_name"))
        .setLastName_ukr(rs.getString("last_name_ukr"))
        .setLogin(rs.getString("login"))
        .setEmail(rs.getString("email"))
        .setPassword(rs.getString("password"))
        .setRole(Role.valueOf(rs.getString("role")))
        .build();
    }

    @Override
    public User makeUnique(Map<Integer, User> existing, User entity) {
        existing.putIfAbsent(entity.getId(), entity);

        return existing.get(entity.getId());
    }

}

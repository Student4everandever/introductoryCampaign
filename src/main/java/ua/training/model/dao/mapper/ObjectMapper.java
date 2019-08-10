package ua.training.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface to map objects from DB
 * @param <T>
 */
public interface ObjectMapper<T> {

    T extractFromResultSet(ResultSet rs) throws SQLException;
}

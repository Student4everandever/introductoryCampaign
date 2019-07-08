package ua.training.model.dao.mapper;

import ua.training.model.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class SubjectMapper implements ObjectMapper<Subject> {
    @Override
    public Subject extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Subject makeUnique(Map<Integer, Subject> existing, Subject entity) {
        return null;
    }
}

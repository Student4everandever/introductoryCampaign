package ua.training.model.dao.mapper;

import ua.training.model.entity.Specialty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class SpecialtyMapper implements ObjectMapper<Specialty> {
    @Override
    public Specialty extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Specialty makeUnique(Map<Integer, Specialty> existing, Specialty entity) {
        return null;
    }
}

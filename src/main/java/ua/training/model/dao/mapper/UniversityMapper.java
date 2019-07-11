package ua.training.model.dao.mapper;

import ua.training.model.entity.University;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UniversityMapper implements ObjectMapper<University> {
    @Override
    public University extractFromResultSet(ResultSet rs) throws SQLException {
        return new University.UniversityBuilder()
                .setId(rs.getInt("university_id"))
                .setName(rs.getString("university_name").replaceAll("\"","”"))
                .setName_ukr(rs.getString("university_name_ukr").replaceAll("\"","\\\""))
                .build();
    }

    @Override
    public University makeUnique(Map<Integer, University> existing, University entity) {
        return null;
    }
}

package ua.training.model.dao.mapper;

import ua.training.model.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements ObjectMapper<Subject> {
    @Override
    public Subject extractFromResultSet(ResultSet rs) throws SQLException {
        return new Subject.SubjectBuilder()
                .setId(rs.getInt("subject_id"))
                .setName(rs.getString("subject_name"))
                .setName_ukr(rs.getString("subject_name_ukr"))
                .build();
    }
}

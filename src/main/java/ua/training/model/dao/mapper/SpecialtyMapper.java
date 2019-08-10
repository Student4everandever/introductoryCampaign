package ua.training.model.dao.mapper;

import ua.training.model.entity.Specialty;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to map Specialty objects from DB
 */
public class SpecialtyMapper implements ObjectMapper<Specialty> {

    /**
     * Maps the data from a JDBC result set to Specialty object
     *
     * @param rs resultSet with data from DB
     * @return Specialty object
     * @throws SQLException exception that provides information on a database access
     *  error or other errors
     */
    @Override
    public Specialty extractFromResultSet(ResultSet rs) throws SQLException {
        return new Specialty.SpecialtyBuilder()
                .setId(rs.getInt("specialty_id"))
                .setTitle(rs.getString("title"))
                .setTitle_ukr(rs.getString("title_ukr"))
                .build();
    }
}

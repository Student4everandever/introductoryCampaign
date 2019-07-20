package ua.training.model.dao.impl;

import ua.training.model.dao.UniversityDao;
import ua.training.model.dao.cp.ConnectionPoolHolder;
import ua.training.model.dao.mapper.UniversityMapper;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.University;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class JDBCUniversityImpl implements UniversityDao {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private UniversityMapper universityMapper = new UniversityMapper();
    private ResourceBundle sqlRequest = ResourceBundle.getBundle("SQLRequests");

    @Override
    public void create(University entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("university_create"))) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getName_ukr());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public University findById(int id) {
        University result = new University();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("university_find_by_id"))) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                result = universityMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<University> findAll() {
        List<University> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString(
                             "university_find_all"))) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                University university = universityMapper.extractFromResultSet(rs);
                result.add(university);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(University university) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString(
                             "university_update"))) {
            ps.setString(1, university.getName());
            ps.setString(2, university.getName_ukr());
            ps.setInt(3, university.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUniversitySpecialties(University university, List<Specialty> specialties) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement deleteQuery = connection
                     .prepareStatement(sqlRequest.getString(
                             "university_specialty_delete"));
             PreparedStatement insertQuery = connection
                     .prepareStatement(sqlRequest.getString(
                             "university_specialty_input"))) {
            connection.setAutoCommit(false);
            deleteQuery.setInt(1, university.getId());
            deleteQuery.executeUpdate();
            for (Specialty specialty : specialties) {
                insertQuery.setInt(1, university.getId());
                insertQuery.setInt(2, specialty.getId());
                insertQuery.addBatch();
            }
                int[] count = insertQuery.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public University findByStringName(String universityString) {
        University university = new University();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("university_find_by_string_name"))) {
            ps.setString(1, universityString);
            ps.setString(2, universityString);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                university = universityMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return university;
    }

    @Override
    public boolean delete(int id) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("university_delete"))) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void close() {

    }

    @Override
    public Optional<University> findByName(University entity) {
        Optional<University> university = Optional.empty();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("university_find_by_name"))) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getName_ukr());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                university = Optional.ofNullable(universityMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return university;
    }
}

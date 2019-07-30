package ua.training.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;
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

    private final static Logger logger = LogManager.getLogger(JDBCUniversityImpl.class);

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private UniversityMapper universityMapper = new UniversityMapper();
    private ResourceBundle sqlRequest = ResourceBundle.getBundle("sqlRequests");

    @Override
    public void create(University entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("university_create"))) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getName_ukr());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(String.format(Messages.JDBC_UNIVERSITY_CREATION_FAIL, entity.getName(), entity.getName_ukr()));
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
            logger.error(String.format(Messages.JDBC_UNIVERSITY_FIND_BY_ID_FAIL, id));
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
        } catch (SQLException e) {
            logger.error(Messages.JDBC_UNIVERSITY_FIND_ALL_FAIL);
            throw new RuntimeException(e);
        }
        return result;
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
            logger.error(String.format(Messages.JDBC_UNIVERSITY_UPDATE_FAIL, university));
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
            logger.error(String.format(Messages.JDBC_UNIVERSITY_UPDATE_SPECIALTIES_FAIL, university));
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
            logger.error(String.format(Messages.JDBC_UNIVERSITY_FIND_BY_STRING_NAME_FAIL, universityString));
            throw new RuntimeException(e);
        }
        return university;
    }

    @Override
    public List<University> getUniversitiesPerPage(int rows, int pageNumber) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("university_find_per_page"))) {

            ps.setInt(1, rows);
            ps.setInt(2, pageNumber * rows - rows);
            ResultSet rs = ps.executeQuery();
            List<University> result = new ArrayList<>();
            while (rs.next()) {
                University university = universityMapper.extractFromResultSet(rs);
                result.add(university);
            }
            return result;
        } catch (SQLException e) {
            logger.error(String.format(Messages.JDBC_UNIVERSITY_FIND_PER_PAGE_FAIL, pageNumber, rows));
            throw new RuntimeException(e);
        }
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
            logger.error(String.format(Messages.JDBC_UNIVERSITY_DELETE_FAIL, id));
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
            logger.error(String.format(Messages.JDBC_UNIVERSITY_FIND_BY_NAME_FAIL, entity.getName(), entity.getName_ukr()));
            throw new RuntimeException(e);
        }
        return university;
    }
}

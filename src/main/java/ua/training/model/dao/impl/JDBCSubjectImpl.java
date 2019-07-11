package ua.training.model.dao.impl;

import ua.training.model.dao.SubjectDao;
import ua.training.model.dao.cp.ConnectionPoolHolder;
import ua.training.model.dao.mapper.SubjectMapper;
import ua.training.model.entity.Subject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class JDBCSubjectImpl implements SubjectDao {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private SubjectMapper subjectMapper = new SubjectMapper();
    private ResourceBundle sqlRequest = ResourceBundle.getBundle("SQLRequests");

    @Override
    public void create(Subject entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("subject_create"))) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getName_ukr());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Subject findById(int id) {
        return null;
    }

    @Override
    public List<Subject> findAll() {
        return null;
    }

    @Override
    public void update(Subject subject) {

    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public Optional<Subject> findByName(Subject entity) {
        Optional<Subject> subject = Optional.empty();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("subject_find_by_name"))) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getName_ukr());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subject = Optional.ofNullable(subjectMapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subject;
    }
}

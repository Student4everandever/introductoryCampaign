package ua.training.model.dao.impl;

import ua.training.model.dao.SubjectDao;
import ua.training.model.dao.cp.ConnectionPoolHolder;
import ua.training.model.dao.mapper.SubjectMapper;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString(
                             "subject_find_all"))) {
            ResultSet rs = ps.executeQuery();
            List<Subject> result = new ArrayList<>();
            while (rs.next()) {
                Subject subject = subjectMapper.extractFromResultSet(rs);
                result.add(subject);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public Subject findByStringName(String name) {
        Subject subject = new Subject();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("subject_find_by_string_name"))) {
            ps.setString(1, name);
            ps.setString(2, name);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subject = subjectMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subject;
    }

    @Override
    public List<Subject> findSubjectBySpecialtyAndNumber(Specialty specialty, int number) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString(
                             "subject_find_by_specialty_and_number"))) {
            ResultSet rs = ps.executeQuery();
            List<Subject> result = new ArrayList<>();
            while (rs.next()) {
                Subject subject = subjectMapper.extractFromResultSet(rs);
                result.add(subject);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Subject> findAllButFirst() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString(
                             "subject_find_all_but_first"))) {
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            List<Subject> result = new ArrayList<>();
            while (rs.next()) {
                Subject subject = subjectMapper.extractFromResultSet(rs);
                result.add(subject);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

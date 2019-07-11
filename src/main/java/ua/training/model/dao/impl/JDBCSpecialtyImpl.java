package ua.training.model.dao.impl;

import ua.training.model.dao.SpecialtyDao;
import ua.training.model.dao.cp.ConnectionPoolHolder;
import ua.training.model.dao.mapper.SpecialtyMapper;
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

public class JDBCSpecialtyImpl implements SpecialtyDao {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private SpecialtyMapper specialtyMapper = new SpecialtyMapper();
    private ResourceBundle sqlRequest = ResourceBundle.getBundle("SQLRequests");


    @Override
    public void create(Specialty entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("specialty_create"))) {
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getTitle_ukr());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Specialty findById(int id) {
        return null;
    }

    @Override
    public List<Specialty> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString(
                             "specialty_find_all"))) {
            ResultSet rs = ps.executeQuery();
            List<Specialty> result = new ArrayList<>();
            while (rs.next()) {
                Specialty specialty = specialtyMapper.extractFromResultSet(rs);
                result.add(specialty);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
        public void update (Specialty specialty){

        }

        @Override
        public boolean delete ( int id){
            return false;
        }

        @Override
        public void close () {

        }

        @Override
        public Optional<Specialty> findByName (Specialty entity){
            Optional<Specialty> specialty = Optional.empty();

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement ps = connection
                         .prepareStatement(sqlRequest.getString("specialty_find_by_name"))) {
                ps.setString(1, entity.getTitle());
                ps.setString(2, entity.getTitle_ukr());

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    specialty = Optional.ofNullable(specialtyMapper.extractFromResultSet(rs));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return specialty;
        }

        @Override
        public List<Specialty> findByUniversity (University university){
            List<Specialty> result = new ArrayList<>();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement universityHasQuery = connection
                         .prepareStatement(sqlRequest.getString(
                                 "specialty_find_by_university"));
                 PreparedStatement specialtyQuery = connection
                         .prepareStatement(sqlRequest.getString(
                                 "specialty_find_by_id"))
            ) {
                universityHasQuery.setInt(1, university.getId());
                connection.setAutoCommit(false);
                ResultSet rs = universityHasQuery.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("specialty_id");
                    specialtyQuery.setInt(1, Integer.parseInt(id));
                    ResultSet resultSet = specialtyQuery.executeQuery();
                    if (resultSet.next()) {
                        Specialty specialty = specialtyMapper.extractFromResultSet(resultSet);
                        result.add(specialty);
                    }
                }
                connection.commit();
                return result;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<Specialty> findAllButUniversity (University university){
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement ps = connection
                         .prepareStatement(sqlRequest.getString(
                                 "specialty_find_all_but_university"))) {
                ps.setInt(1, university.getId());
                ResultSet rs = ps.executeQuery();
                List<Specialty> result = new ArrayList<>();
                while (rs.next()) {
                    Specialty specialty = specialtyMapper.extractFromResultSet(rs);
                    result.add(specialty);
                }
                return result;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

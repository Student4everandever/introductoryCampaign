package ua.training.model.dao.impl;

import ua.training.model.dao.SpecialtyDao;
import ua.training.model.dao.cp.ConnectionPoolHolder;
import ua.training.model.dao.mapper.SpecialtyMapper;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;
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
    private ResourceBundle sqlRequest = ResourceBundle.getBundle("sqlRequests");


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
        Specialty result = new Specialty();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("specialty_find_by_id"))) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                result = specialtyMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
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
        public boolean delete (int id){

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement ps = connection
                         .prepareStatement(sqlRequest.getString("specialty_delete"))) {
                ps.setInt(1, id);
                ps.executeUpdate();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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

    @Override
    public void updateSpecialtyWithSubjects(Specialty specialty, List<Subject> subjects2, List<Subject> subjects3) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement specialty_query = connection
                     .prepareStatement(sqlRequest.getString(
                             "specialty_update"));
             PreparedStatement deleteQuery = connection
                     .prepareStatement(sqlRequest.getString(
                             "specialty_subjects_delete"));
             PreparedStatement insertQuery1 = connection
                     .prepareStatement(sqlRequest.getString(
                             "specialty_subjects_input"));
             PreparedStatement insertQuery2 = connection
                     .prepareStatement(sqlRequest.getString(
                             "specialty_subjects_input"))) {
            connection.setAutoCommit(false);
            specialty_query.setString(1, specialty.getTitle());
            specialty_query.setString(2, specialty.getTitle_ukr());
            specialty_query.setInt(3, specialty.getId());
            specialty_query.executeUpdate();
            deleteQuery.setInt(1, specialty.getId());
            deleteQuery.executeUpdate();
            for (Subject subject : subjects2) {
                insertQuery2.setInt(1, specialty.getId());
                insertQuery2.setInt(2, subject.getId());
                insertQuery2.setInt(3, 2);
                insertQuery2.addBatch();
            }
            int[] count = insertQuery1.executeBatch();

            for (Subject subject : subjects3) {
                insertQuery2.setInt(1, specialty.getId());
                insertQuery2.setInt(2, subject.getId());
                insertQuery2.setInt(3, 3);
                insertQuery2.addBatch();
            }
            int[] count2 = insertQuery2.executeBatch();

            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createWithUniversityAndSubjects(Specialty specialty, University university, List<Subject> subjects2, List<Subject> subjects3) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement createSpecialtyQuery = connection
                     .prepareStatement(sqlRequest.getString("specialty_create"));
             PreparedStatement getIdQuery = connection
                     .prepareStatement(sqlRequest.getString("specialty_get_last_inserted_id"));
             PreparedStatement universityHasSpecialtyQuery = connection
                     .prepareStatement(sqlRequest.getString("university_specialty_input"));
             PreparedStatement subjectsQuery1 = connection
                     .prepareStatement(sqlRequest.getString("specialty_subjects_input"));
             PreparedStatement subjectsQuery2 = connection
                     .prepareStatement(sqlRequest.getString("specialty_subjects_input"))
        ) {
            connection.setAutoCommit(false);
            createSpecialtyQuery.setString(1, specialty.getTitle());
            createSpecialtyQuery.setString(2, specialty.getTitle_ukr());
            createSpecialtyQuery.executeUpdate();
            ResultSet rs = getIdQuery.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");

                universityHasSpecialtyQuery.setInt(1, university.getId());
                universityHasSpecialtyQuery.setInt(2, id);
                universityHasSpecialtyQuery.executeUpdate();
                for (Subject subject : subjects2) {
                    subjectsQuery1.setInt(1, id);
                    subjectsQuery1.setInt(2, subject.getId());
                    subjectsQuery1.setInt(3, 2);
                    subjectsQuery1.addBatch();
                }
                int[] count = subjectsQuery1.executeBatch();

                for (Subject subject : subjects3) {
                    subjectsQuery2.setInt(1, id);
                    subjectsQuery2.setInt(2, subject.getId());
                    subjectsQuery2.setInt(3, 3);
                    subjectsQuery2.addBatch();
                }
                int[] count2 = subjectsQuery2.executeBatch();
                connection.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Specialty findByNameString(String specialtyString) {
        Specialty specialty = new Specialty();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("specialty_find_by_string_name"))) {
            ps.setString(1, specialtyString);
            ps.setString(2, specialtyString);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                specialty = specialtyMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialty;
    }
}

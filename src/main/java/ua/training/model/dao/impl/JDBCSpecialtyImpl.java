package ua.training.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.LoggerMessages;
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

/**
 * Class that accesses the specialty table of DB
 */
public class JDBCSpecialtyImpl implements SpecialtyDao {

    private final static Logger logger = LogManager.getLogger(JDBCSubjectImpl.class);
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private SpecialtyMapper specialtyMapper = new SpecialtyMapper();
    private ResourceBundle sqlRequest = ResourceBundle.getBundle("sqlRequests");

    /**
     * Finds Specialty in DB by it's id
     *
     * @param id id to find
     * @return Specialty object from DB
     */
    @Override
    public Specialty findById(int id) {
        Specialty result = new Specialty();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("specialty_find_by_id"))) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = specialtyMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            logger.error(String.format(LoggerMessages.JDBC_SPECIALTY_FIND_BY_ID_FAIL, id));
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Finds all Specialties in DB
     *
     * @return List of Specialties objects from DB
     */
    @Override
    public List<Specialty> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString(
                             "specialty_find_all"))) {
            return getSpecialties(ps);
        } catch (SQLException e) {
            logger.error(LoggerMessages.JDBC_SPECIALTY_FIND_ALL_FAIL);
            throw new RuntimeException(e);
        }
    }

    private List<Specialty> getSpecialties(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        List<Specialty> result = new ArrayList<>();
        while (rs.next()) {
            Specialty specialty = specialtyMapper.extractFromResultSet(rs);
            result.add(specialty);
        }
        return result;
    }

    /**
     * Deletes Specialty from DB by it's id
     *
     * @param id id of a Specialty to delete
     * @return boolean true if deletion is performed, false if not performed
     */
    @Override
    public boolean delete(int id) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("specialty_delete"))) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(String.format(LoggerMessages.JDBC_SPECIALTY_DELETE_FAIL, id));
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds Specialty in DB by it's name
     *
     * @param entity Specialty to look for
     * @return Optional of Specialty object from DB
     */
    @Override
    public Optional<Specialty> findByName(Specialty entity) {
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
            logger.error(String.format(LoggerMessages.JDBC_SPECIALTY_FIND_BY_NAME_FAIL, entity.getTitle(), entity.getTitle_ukr()));
            throw new RuntimeException(e);
        }
        return specialty;
    }

    /**
     * Finds all Specialties for certain university in DB
     *
     * @param university to find specialties for
     * @return List of Specialties from DB
     */
    @Override
    public List<Specialty> findByUniversity(University university) {
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
            logger.error(String.format(LoggerMessages.JDBC_SPECIALTY_FIND_FOR_UNIVERSITY_FAIL, university));
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds all Specialties that university do not contain
     *
     * @param university to exclude specialties
     * @return List of Specialties from DB
     */
    @Override
    public List<Specialty> findAllButUniversity(University university) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString(
                             "specialty_find_all_but_university"))) {
            ps.setInt(1, university.getId());
            return getSpecialties(ps);
        } catch (SQLException e) {
            logger.error(String.format(LoggerMessages.JDBC_SPECIALTY_FIND_NON_UNIVERSITY_FAIL, university));
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates Specialty object changes name and subjects
     *
     * @param specialty specialty to update
     * @param subjects2 subjects for exam 2
     * @param subjects3 subjects for exam 3
     */
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
            logger.error(String.format(LoggerMessages.JDBC_SPECIALTY_UPDATE_WITH_SUBJECTS_FAIL, specialty));
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates new Specialty for certain university(ies) that contain subjects
     *
     * @param specialty  Specialty to create
     * @param university University to connect with
     * @param subjects2  subjects for exam 2
     * @param subjects3  subjects for exam 3
     */
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
            if (rs.next()) {
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
            logger.error(String.format(LoggerMessages.JDBC_SPECIALTY_CREATE_WITH_UNIVERSITY_AND_SUBJECT_FAIL, specialty.getTitle(), specialty.getTitle_ukr()));
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds specialty by name in DB
     *
     * @param specialtyString Specialty name to look for
     * @return Specialty object from DB
     */
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
            logger.error(String.format(LoggerMessages.JDBC_SPECIALTY_FIND_BY_STRING_NAME_FAIL, specialtyString));
            throw new RuntimeException(e);
        }
        return specialty;
    }

    /**
     * Finds Specialties to display on one page (for pagination)
     *
     * @param rows       number of rows to display
     * @param pageNumber page number to find specialties for
     * @return List of specialties from DB
     */
    @Override
    public List<Specialty> getSpecialtiesPerPage(int rows, int pageNumber) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(sqlRequest.getString("specialty_find_per_page"))) {

            ps.setInt(1, rows);
            ps.setInt(2, pageNumber * rows - rows);
            return getSpecialties(ps);
        } catch (SQLException e) {
            logger.error(String.format(LoggerMessages.JDBC_SPECIALTY_FIND_PER_PAGE, pageNumber, rows));
            throw new RuntimeException(e);
        }
    }
}

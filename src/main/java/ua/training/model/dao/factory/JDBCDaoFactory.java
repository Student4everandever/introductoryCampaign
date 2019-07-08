package ua.training.model.dao.factory;

import ua.training.model.dao.SpecialtyDao;
import ua.training.model.dao.SubjectDao;
import ua.training.model.dao.UniversityDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.cp.ConnectionPoolHolder;
import ua.training.model.dao.impl.JDBCSpecialtyImpl;
import ua.training.model.dao.impl.JDBCSubjectImpl;
import ua.training.model.dao.impl.JDBCUniversityImpl;
import ua.training.model.dao.impl.JDBCUserImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public SpecialtyDao createSpecialtyDao() {
        return new JDBCSpecialtyImpl();
    }

    @Override
    public SubjectDao createSubjectDao() {
        return new JDBCSubjectImpl();
    }

    @Override
    public UniversityDao createUniversityDao() {
        return new JDBCUniversityImpl();
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserImpl();
    }


    private Connection getConnection() {
        try{
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

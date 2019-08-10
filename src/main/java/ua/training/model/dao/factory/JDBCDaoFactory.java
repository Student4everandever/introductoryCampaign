package ua.training.model.dao.factory;

import ua.training.model.dao.SpecialtyDao;
import ua.training.model.dao.SubjectDao;
import ua.training.model.dao.UniversityDao;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.JDBCSpecialtyImpl;
import ua.training.model.dao.impl.JDBCSubjectImpl;
import ua.training.model.dao.impl.JDBCUniversityImpl;
import ua.training.model.dao.impl.JDBCUserImpl;

public class JDBCDaoFactory extends DaoFactory {

    /**
     * Produces specialty implementation to work with DB
     * @return SpecialtyDao object to perform actions with specialties table in DB
     */
    @Override
    public SpecialtyDao createSpecialtyDao() {
        return new JDBCSpecialtyImpl();
    }

    /**
     * Produces subject implementation to work with DB
     * @return SubjectDao object to perform actions with subject table in DB
     */
    @Override
    public SubjectDao createSubjectDao() {
        return new JDBCSubjectImpl();
    }

    /**
     * Produces university implementation to work with DB
     * @return UniversityDao object to perform actions with university table in DB
     */
    @Override
    public UniversityDao createUniversityDao() {
        return new JDBCUniversityImpl();
    }

    /**
     * Produces user implementation to work with DB
     * @return UserDao object to perform actions with user table in DB
     */
    @Override
    public UserDao createUserDao() {
        return new JDBCUserImpl();
    }
}

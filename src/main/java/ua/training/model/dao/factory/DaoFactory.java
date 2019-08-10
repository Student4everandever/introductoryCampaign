package ua.training.model.dao.factory;


import ua.training.model.dao.SpecialtyDao;
import ua.training.model.dao.SubjectDao;
import ua.training.model.dao.UniversityDao;
import ua.training.model.dao.UserDao;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract SpecialtyDao createSpecialtyDao();
    public abstract UserDao createUserDao();
    public abstract SubjectDao createSubjectDao();
    public abstract UniversityDao createUniversityDao();

    /**
     * Produces a different DAOs depending on which one is needed to work with DB
     * @return DaoFactory object (singleton) to obtain and store data in DB
     */
    public static DaoFactory getInstance() {

        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}

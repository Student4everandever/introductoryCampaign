package ua.training.model.dao.factory;


import ua.training.model.dao.SpecialtyDao;
import ua.training.model.dao.SubjectDao;
import ua.training.model.dao.UniversityDao;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;
import ua.training.model.entity.University;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public abstract SpecialtyDao createSpecialtyDao();
    public abstract UserDao createUserDao();
    public abstract SubjectDao createSubjectDao();
    public abstract UniversityDao createUniversityDao();

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

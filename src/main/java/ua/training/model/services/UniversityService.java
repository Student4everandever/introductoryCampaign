package ua.training.model.services;

import ua.training.model.dao.UniversityDao;
import ua.training.model.dao.factory.DaoFactory;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.University;

import java.util.List;

import static ua.training.constants.Regex.REGEX_UNIVERSITY_LAT;
import static ua.training.constants.Regex.REGEX_UNIVERSITY_UKR;

/**
 * Service class for University
 */
public class UniversityService {
    private static UniversityDao universityDao = DaoFactory.getInstance().createUniversityDao();

    public boolean validateUniversityData(String name, String name_ukr) {
        return name.matches(REGEX_UNIVERSITY_LAT) &&
                name_ukr.matches(REGEX_UNIVERSITY_UKR);
    }

    public boolean universityExists(University university) {
        return universityDao.findByName(university).isPresent();
    }

    public void addUniversity(University university) {
        universityDao.create(university);
    }

    public List<University> getAllUniversities() {
        return universityDao.findAll();
    }

    public University getUniversityById(int id) {
        return universityDao.findById(id);
    }

    public void editUniversity(University university) {
        universityDao.update(university);
    }

    public void editUniversitySpecialties(University university, List<Specialty> universitySpecialties) {
        universityDao.updateUniversitySpecialties(university, universitySpecialties);
    }

    public boolean deleteUniversity(int id) {
        return universityDao.delete(id);
    }

    public University getUniversityByName(String universityString) {
        return universityDao.findByStringName(universityString);
    }

    public int getNumberOfPages(int rows) {
        int numberOfUniversities = universityDao.findAll().size();
        return (int) Math.ceil((double) numberOfUniversities / rows);
    }

    public List<University> getUniversitiesPerPage(int rows, int pageNumber) {
        return universityDao.getUniversitiesPerPage(rows, pageNumber);
    }
}

package ua.training.model.services;

import ua.training.model.dao.SpecialtyDao;
import ua.training.model.dao.factory.DaoFactory;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;
import ua.training.model.entity.University;

import java.util.List;

import static ua.training.constants.Regex.REGEX_SPECIALTY_LAT;
import static ua.training.constants.Regex.REGEX_SPECIALTY_UKR;

public class SpecialtyService {
    private static SpecialtyDao specialtyDao = DaoFactory.getInstance().createSpecialtyDao();

    public boolean validateSpecialtyData(String title, String title_ukr) {
        return title.matches(REGEX_SPECIALTY_LAT) &&
                title_ukr.matches(REGEX_SPECIALTY_UKR);
    }

    public boolean specialtyExists(Specialty specialty) {
        return specialtyDao.findByName(specialty).isPresent();
    }

    public void addSpecialty(Specialty specialty, University university, List<Subject> subjects2, List<Subject> subjects3) {
        specialtyDao.createWithUniversityAndSubjects(specialty, university, subjects2, subjects3);
    }

    public List<Specialty> getUniversitySpecialties(University university) {
        return specialtyDao.findByUniversity(university);
    }

    public List<Specialty> getNonUniversitySpecialties(University university) {
        return specialtyDao.findAllButUniversity(university);
    }

    public List<Specialty> getAllSpecialties() {
        return specialtyDao.findAll();
    }

    public Specialty getSpecialtyById(int id) {
        return specialtyDao.findById(id);
    }

    public void editSpecialty(Specialty specialty, List<Subject> subjects2, List<Subject> subjects3) {
        specialtyDao.updateSpecialtyWithSubjects(specialty, subjects2, subjects3);
    }

    public boolean deleteSpecialty(int id) {
        return specialtyDao.delete(id);
    }

    public Specialty getSpecialtyByNameString(String specialtyString) {
        return specialtyDao.findByNameString(specialtyString);
    }

    public int getNumberOfPages(int rows) {
        int numberOfSpecialties = specialtyDao.findAll().size();
        return (int) Math.ceil((double) numberOfSpecialties / rows);
    }

    public List<Specialty> getSpecialtiesPerPage(int rows, int pageNumber) {
        return specialtyDao.getSpecialtiesPerPage(rows, pageNumber);
    }
}

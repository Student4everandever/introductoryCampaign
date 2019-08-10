package ua.training.model.services;

import ua.training.model.dao.SpecialtyDao;
import ua.training.model.dao.factory.DaoFactory;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;
import ua.training.model.entity.University;

import java.util.List;

import static ua.training.constants.Regex.REGEX_SPECIALTY_LAT;
import static ua.training.constants.Regex.REGEX_SPECIALTY_UKR;

/**
 * Service class for Specialty
 */
public class SpecialtyService {
    private static SpecialtyDao specialtyDao = DaoFactory.getInstance().createSpecialtyDao();

    /**
     * Validates data entered by user
     *
     * @param title     Title of Specialty to validate
     * @param title_ukr Title in ukrainian of Specialty to validate
     * @return Boolean true if all data is valid, false if any data is not valid
     */
    public boolean validateSpecialtyData(String title, String title_ukr) {
        return title.matches(REGEX_SPECIALTY_LAT) &&
                title_ukr.matches(REGEX_SPECIALTY_UKR);
    }

    /**
     * Checks if Specialty exists in DB
     *
     * @param specialty Specialty to check for
     * @return Boolean true if Specialty exists, false if Specialty not exists
     */
    public boolean specialtyExists(Specialty specialty) {
        return specialtyDao.findByName(specialty).isPresent();
    }

    /**
     * Adds Specialty to DB
     *
     * @param specialty  Specialty to Add
     * @param university University to connect Specialty to
     * @param subjects2  Subjects for exam 2
     * @param subjects3  Subjects for exam 3
     */
    public void addSpecialty(Specialty specialty, University university, List<Subject> subjects2, List<Subject> subjects3) {
        specialtyDao.createWithUniversityAndSubjects(specialty, university, subjects2, subjects3);
    }

    /**
     * Returns all Specialties for given University
     *
     * @param university University to find Specialties for
     * @return List of Specialties for given University from DB
     */
    public List<Specialty> getUniversitySpecialties(University university) {
        return specialtyDao.findByUniversity(university);
    }

    /**
     * Returns Specialties that given University do not contain
     *
     * @param university University to exclude Specialties for
     * @return List of Specialties except the once that given university has
     */
    public List<Specialty> getNonUniversitySpecialties(University university) {
        return specialtyDao.findAllButUniversity(university);
    }

    /**
     * Returns all Specialties from DB
     *
     * @return List with all Specialties from DB
     */
    public List<Specialty> getAllSpecialties() {
        return specialtyDao.findAll();
    }

    /**
     * Retuns Specialty from DB by it's id
     *
     * @param id id of the Specialty to look for
     * @return Specialty from DB
     */
    public Specialty getSpecialtyById(int id) {
        return specialtyDao.findById(id);
    }

    /**
     * Changes the given Specialty data
     *
     * @param specialty Specialty to change data
     * @param subjects2 Subject to set for exam 2
     * @param subjects3 Subject to set for exam 3
     */
    public void editSpecialty(Specialty specialty, List<Subject> subjects2, List<Subject> subjects3) {
        specialtyDao.updateSpecialtyWithSubjects(specialty, subjects2, subjects3);
    }

    /**
     * Deletes Specialty from DB
     *
     * @param id id of the Specialty to delete
     * @return Boolean true if deletion performed, false if deletion not performed
     */
    public boolean deleteSpecialty(int id) {
        return specialtyDao.delete(id);
    }

    /**
     * Returns Specialty from DB by String name
     *
     * @param specialtyString String name to look for Specialty
     * @return Specialty object from DB
     */
    public Specialty getSpecialtyByNameString(String specialtyString) {
        return specialtyDao.findByNameString(specialtyString);
    }

    /**
     * Returns number of pages for displaying all Specialties, according to
     * number of rows to display on the page and Specialties count
     *
     * @param rows number of rows to display on the page
     * @return int number of pages for displaying all Specialties
     */
    public int getNumberOfPages(int rows) {
        int numberOfSpecialties = specialtyDao.findAll().size();
        return (int) Math.ceil((double) numberOfSpecialties / rows);
    }

    /**
     * Returns Specialties to display on one page,
     * depending on the number of rows to display on the page and page number
     *
     * @param rows Number of rows on the page
     * @param pageNumber Page number to display specialties for
     * @return List of specialties to display
     */
    public List<Specialty> getSpecialtiesPerPage(int rows, int pageNumber) {
        return specialtyDao.getSpecialtiesPerPage(rows, pageNumber);
    }
}

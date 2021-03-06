package ua.training.model.dao;

import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;
import ua.training.model.entity.University;

import java.util.List;
import java.util.Optional;

public interface SpecialtyDao {
    Specialty findById(int id);

    List<Specialty> findAll();

    boolean delete(int id);

    Optional<Specialty> findByName(Specialty specialty);

    List<Specialty> findByUniversity(University university);

    List<Specialty> findAllButUniversity(University university);

    void updateSpecialtyWithSubjects(Specialty specialty, List<Subject> subjects2, List<Subject> subjects3);

    void createWithUniversityAndSubjects(Specialty specialty, University university, List<Subject> subjects2, List<Subject> subjects3);

    Specialty findByNameString(String specialtyString);

    List<Specialty> getSpecialtiesPerPage(int rows, int pageNumber);
}

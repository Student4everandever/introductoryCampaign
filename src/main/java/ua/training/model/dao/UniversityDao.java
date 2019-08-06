package ua.training.model.dao;

import ua.training.model.entity.Specialty;
import ua.training.model.entity.University;

import java.util.List;
import java.util.Optional;

public interface UniversityDao {
    boolean delete(int id);

    Optional<University> findByName(University university);

    University findById(int id);

    List<University> findAll();

    void update(University university);

    void updateUniversitySpecialties(University university, List<Specialty> specialties);

    University findByStringName(String universityString);

    List<University> getUniversitiesPerPage(int rows, int pageNumber);

    void create(University university);
}

package ua.training.model.dao;

import ua.training.model.entity.Specialty;
import ua.training.model.entity.University;

import java.util.List;
import java.util.Optional;

public interface UniversityDao extends GenericDao<University> {
    Optional<University> findByName(University university);

    void updateUniversitySpecialties(University university, List<Specialty> specialties);

    University findByStringName(String universityString);
}

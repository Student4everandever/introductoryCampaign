package ua.training.model.dao;

import ua.training.model.entity.Specialty;
import ua.training.model.entity.University;

import java.util.List;
import java.util.Optional;

public interface SpecialtyDao extends GenericDao<Specialty> {
    Optional<Specialty> findByName(Specialty specialty);

    List<Specialty> findByUniversity(University university);

    List<Specialty> findAllButUniversity(University university);
}

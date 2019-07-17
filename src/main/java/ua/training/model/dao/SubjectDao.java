package ua.training.model.dao;

import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SubjectDao extends GenericDao<Subject> {
    Optional<Subject> findByName(Subject subject);

    Subject findByStringName(String name);

    List<Subject> findSubjectBySpecialtyAndNumber(Specialty specialty, int number);

    List<Subject> findAllButFirst();
}

package ua.training.model.dao;

import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;
import ua.training.model.entity.University;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface SubjectDao {
    void create(Subject entity);

    List<Subject> findAll();

    Optional<Subject> findByName(Subject subject);

    Subject findByStringName(String name);

    List<Subject> findSubjectBySpecialtyAndNumber(Specialty specialty, int number);

    List<Subject> findAllButFirst();

    void addSubjectsToUser(User user, University university, Specialty specialty, List<Subject> subjects);

    List<Subject> findSubjectsOfUser(User user);
}

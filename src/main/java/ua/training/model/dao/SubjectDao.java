package ua.training.model.dao;

import ua.training.model.entity.Subject;

import java.util.Optional;

public interface SubjectDao extends GenericDao<Subject> {
    Optional<Subject> findByName(Subject subject);
}

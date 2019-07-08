package ua.training.model.dao.impl;

import ua.training.model.dao.SubjectDao;
import ua.training.model.entity.Subject;

import java.util.List;

public class JDBCSubjectImpl implements SubjectDao {
    @Override
    public void create(Subject entity) {

    }

    @Override
    public Subject findById(int id) {
        return null;
    }

    @Override
    public List<Subject> findAll() {
        return null;
    }

    @Override
    public void update(Subject subject) {

    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() {

    }
}

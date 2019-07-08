package ua.training.model.dao.impl;

import ua.training.model.dao.UniversityDao;
import ua.training.model.entity.University;

import java.util.List;

public class JDBCUniversityImpl implements UniversityDao {
    @Override
    public void create(University entity) {

    }

    @Override
    public University findById(int id) {
        return null;
    }

    @Override
    public List<University> findAll() {
        return null;
    }

    @Override
    public void update(University university) {

    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() {

    }
}

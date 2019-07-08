package ua.training.model.dao.impl;

import ua.training.model.dao.SpecialtyDao;
import ua.training.model.entity.Specialty;

import java.util.List;

public class JDBCSpecialtyImpl implements SpecialtyDao {
    @Override
    public void create(Specialty entity) {

    }

    @Override
    public Specialty findById(int id) {
        return null;
    }

    @Override
    public List<Specialty> findAll() {
        return null;
    }

    @Override
    public void update(Specialty specialty) {

    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() {

    }
}

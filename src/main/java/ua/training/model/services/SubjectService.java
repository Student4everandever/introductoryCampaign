package ua.training.model.services;

import ua.training.model.dao.SubjectDao;
import ua.training.model.dao.factory.DaoFactory;
import ua.training.model.entity.Specialty;
import ua.training.model.entity.Subject;
import ua.training.model.entity.University;
import ua.training.model.entity.User;

import java.util.ArrayList;
import java.util.List;

import static ua.training.constants.Regex.REGEX_SUBJECT_LAT;
import static ua.training.constants.Regex.REGEX_SUBJECT_UKR;

/**
 * Service class for Subject
 */
public class SubjectService {

    private static SubjectDao subjectDao = DaoFactory.getInstance().createSubjectDao();


    public boolean validateSubjectData(String name, String name_ukr) {
        return name.matches(REGEX_SUBJECT_LAT) &&
                name_ukr.matches(REGEX_SUBJECT_UKR);
    }

    public boolean subjectExists(Subject subject) {
        return subjectDao.findByName(subject).isPresent();

    }

    public void addSubject(Subject subject) {
        subjectDao.create(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectDao.findAll();
    }

    public Subject findSubjectByString(String name) {
        return subjectDao.findByStringName(name);
    }

    public List<Subject> setSubjectsList(String[] subjectsStrings){
        List<Subject> subjects = new ArrayList<>();

            for (String subject : subjectsStrings) {
                subjects.add(findSubjectByString(subject));
            }
        return subjects;
    }

    public List<Subject> getSubjectsBySpecialtyAndNumber(Specialty specialty, int number) {
        return subjectDao.findSubjectBySpecialtyAndNumber(specialty, number);
    }

    public List<Subject> getAllButFirst() {
        return subjectDao.findAllButFirst();
    }

    public void addSubjectsToUser(User user, University university, Specialty specialty, List<Subject> subjects) {
        subjectDao.addSubjectsToUser(user, university, specialty, subjects);
    }

    public List<Subject> getUserSubjects(User user) {
        return subjectDao.findSubjectsOfUser(user);
    }
}

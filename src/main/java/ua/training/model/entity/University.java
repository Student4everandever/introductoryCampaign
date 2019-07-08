package ua.training.model.entity;

import java.util.List;
import java.util.Objects;

public class University {
    private int id;
    private String appellation;
    private String appellation_ukr;

    private List<User> students;
    private List<Specialty> specialties;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getAppellation_ukr() {
        return appellation_ukr;
    }

    public void setAppellation_ukr(String appellation_ukr) {
        this.appellation_ukr = appellation_ukr;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return id == that.id &&
                Objects.equals(appellation, that.appellation) &&
                Objects.equals(appellation_ukr, that.appellation_ukr) &&
                Objects.equals(students, that.students) &&
                Objects.equals(specialties, that.specialties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appellation, appellation_ukr, students, specialties);
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", appellation='" + appellation + '\'' +
                ", appellation_ukr='" + appellation_ukr + '\'' +
                '}';
    }


    public static final class UniversityBuilder {
        private int id;
        private String appellation;
        private String appellation_ukr;
        private List<User> students;
        private List<Specialty> specialties;

        private UniversityBuilder() {
        }

        public static UniversityBuilder anUniversity() {
            return new UniversityBuilder();
        }

        public UniversityBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UniversityBuilder setAppellation(String appellation) {
            this.appellation = appellation;
            return this;
        }

        public UniversityBuilder setAppellation_ukr(String appellation_ukr) {
            this.appellation_ukr = appellation_ukr;
            return this;
        }

        public UniversityBuilder setStudents(List<User> students) {
            this.students = students;
            return this;
        }

        public UniversityBuilder setSpecialties(List<Specialty> specialties) {
            this.specialties = specialties;
            return this;
        }

        public University build() {
            University university = new University();
            university.setId(id);
            university.setAppellation(appellation);
            university.setAppellation_ukr(appellation_ukr);
            university.setStudents(students);
            university.setSpecialties(specialties);
            return university;
        }
    }
}

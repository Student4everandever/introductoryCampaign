package ua.training.model.entity;

import java.util.List;
import java.util.Objects;

public class University {
    private int id;
    private String name;
    private String name_ukr;

    private List<User> students;
    private List<Specialty> specialties;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_ukr() {
        return name_ukr;
    }

    public void setName_ukr(String name_ukr) {
        this.name_ukr = name_ukr;
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
                Objects.equals(name, that.name) &&
                Objects.equals(name_ukr, that.name_ukr) &&
                Objects.equals(students, that.students) &&
                Objects.equals(specialties, that.specialties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, name_ukr, students, specialties);
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", name_ukr='" + name_ukr + '\'' +
                '}';
    }


    public static final class UniversityBuilder {
        private int id;
        private String name;
        private String name_ukr;
        private List<User> students;
        private List<Specialty> specialties;

        public UniversityBuilder() {
        }

        public UniversityBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UniversityBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UniversityBuilder setName_ukr(String name_ukr) {
            this.name_ukr = name_ukr;
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
            university.setName(name);
            university.setName_ukr(name_ukr);
            university.setStudents(students);
            university.setSpecialties(specialties);
            return university;
        }
    }
}

package ua.training.model.entity;

import java.util.List;
import java.util.Objects;

public class Specialty {
    private int id;
    private String title;
    private String title_ukr;

    private List<Subject> requiredExams;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_ukr() {
        return title_ukr;
    }

    public void setTitle_ukr(String title_ukr) {
        this.title_ukr = title_ukr;
    }

    public List<Subject> getRequiredExams() {
        return requiredExams;
    }

    public void setRequiredExams(List<Subject> requiredExams) {
        this.requiredExams = requiredExams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialty specialty = (Specialty) o;
        return id == specialty.id &&
                Objects.equals(title, specialty.title) &&
                Objects.equals(title_ukr, specialty.title_ukr) &&
                Objects.equals(requiredExams, specialty.requiredExams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, title_ukr, requiredExams);
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", title_ukr='" + title_ukr + '\'' +
                '}';
    }


    public static final class SpecialtyBuilder {
        private int id;
        private String title;
        private String title_ukr;
        private List<Subject> requiredExams;

        public SpecialtyBuilder() {
        }

        public static SpecialtyBuilder aSpecialty() {
            return new SpecialtyBuilder();
        }

        public SpecialtyBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public SpecialtyBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public SpecialtyBuilder setTitle_ukr(String title_ukr) {
            this.title_ukr = title_ukr;
            return this;
        }

        public SpecialtyBuilder setRequiredExams(List<Subject> requiredExams) {
            this.requiredExams = requiredExams;
            return this;
        }

        public Specialty build() {
            Specialty specialty = new Specialty();
            specialty.setId(id);
            specialty.setTitle(title);
            specialty.setTitle_ukr(title_ukr);
            specialty.setRequiredExams(requiredExams);
            return specialty;
        }
    }
}

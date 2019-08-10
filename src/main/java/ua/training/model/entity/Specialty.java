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

    /**
     * Compares Specialty object with given object
     *
     * @param o Object to compare with
     * @return Boolean true if objects are equal, false if not equal
     */
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

    /**
     * Computes a hash code of Specialty object
     *
     * @return int hash code for Specialty
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, title, title_ukr, requiredExams);
    }

    /**
     * Returns String visualization of Specialty
     *
     * @return String with data about Specialty
     */
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

        /**
         * Constructs SpecialtyBuilder without parameters
         */
        public SpecialtyBuilder() {
        }

        /**
         * Sets id of SpecialtyBuilder
         *
         * @param id id to set
         * @return SpecialtyBuilder object
         */
        public SpecialtyBuilder setId(int id) {
            this.id = id;
            return this;
        }

        /**
         * Sets title of SpecialtyBuilder
         *
         * @param title title to set
         * @return SpecialtyBuilder object
         */
        public SpecialtyBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets ukrainian title of SpecialtyBuilder
         *
         * @param title_ukr title to set
         * @return SpecialtyBuilder object
         */
        public SpecialtyBuilder setTitle_ukr(String title_ukr) {
            this.title_ukr = title_ukr;
            return this;
        }

        /**
         * Builds SpecialtyBuilder object with builder setters
         *
         * @return SpecialtyBuilder object
         */
        public Specialty build() {
            Specialty specialty = new Specialty();
            specialty.setId(id);
            specialty.setTitle(title);
            specialty.setTitle_ukr(title_ukr);
            return specialty;
        }
    }
}

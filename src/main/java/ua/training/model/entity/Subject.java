package ua.training.model.entity;

import java.util.Objects;

public class Subject {
    private int id;
    private String name;
    private String name_ukr;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id &&
                Objects.equals(name, subject.name) &&
                Objects.equals(name_ukr, subject.name_ukr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, name_ukr);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", name_ukr='" + name_ukr + '\'' +
                '}';
    }


    public static final class SubjectBuilder {
        private int id;
        private String name;
        private String name_ukr;

        public SubjectBuilder() {
        }

        public SubjectBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public SubjectBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public SubjectBuilder setName_ukr(String name_ukr) {
            this.name_ukr = name_ukr;
            return this;
        }

        public Subject build() {
            Subject subject = new Subject();
            subject.setId(id);
            subject.setName(name);
            subject.setName_ukr(name_ukr);
            return subject;
        }
    }
}

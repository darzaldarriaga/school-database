package com.darwinsofttech.school.repository.subject;

import com.darwinsofttech.school.repository.schedule.Schedule;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Version
    private int version;

    @Column(nullable = false)
    private String subjectCode;

    private String subjectDescription;

    @OneToMany(mappedBy = "subject", targetEntity = Schedule.class, orphanRemoval = true)
    private List<Schedule> schedules;

    public Subject() {
    }

    public Subject(String subjectCode, String subjectDescription) {
        this.subjectCode = subjectCode;
        this.subjectDescription = subjectDescription;
    }

    public Subject(String subjectCode, String subjectDescription, List<Schedule> schedules) {
        this.subjectCode = subjectCode;
        this.subjectDescription = subjectDescription;
        this.schedules = schedules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return subjectCode + " - " + subjectDescription;
    }
}

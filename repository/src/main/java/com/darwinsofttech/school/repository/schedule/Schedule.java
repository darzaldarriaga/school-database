package com.darwinsofttech.school.repository.schedule;

import com.darwinsofttech.school.repository.student.Student;
import com.darwinsofttech.school.repository.subject.Subject;
import com.darwinsofttech.school.repository.teacher.Teacher;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Version
    private int version;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subjectId", referencedColumnName = "id")
    private Subject subject;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teacherId", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "studentSchedules",
            joinColumns = @JoinColumn(name = "scheduleId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "studentId", referencedColumnName = "id"))
    private List<Student> students;

    private String days;
    private String time;

    public Schedule() {
    }

    public Schedule(Subject subject, Teacher teacher, String days, String time) {
        this.subject = subject;
        this.teacher = teacher;
        this.days = days;
        this.time = time;
    }

    public Schedule(Subject subject, Teacher teacher, String days, String time, List<Student> students) {
        this.subject = subject;
        this.teacher = teacher;
        this.days = days;
        this.time = time;
        this.students = students;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id == schedule.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", version=" + version +
                ", subject=" + subject +
                ", teacher=" + teacher +
                ", days=" + days +
                ", time=" + time +
                "}";
    }
}

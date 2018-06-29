package com.darwinsofttech.school.repository.student;

import com.darwinsofttech.school.repository.schedule.Schedule;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Version
    private int version;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;
    private String middleName;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    public Student() {
    }

    public Student(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    public Student(String lastName, String firstName, String middleName, List<Schedule> schedules) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", version=" + version +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }
}

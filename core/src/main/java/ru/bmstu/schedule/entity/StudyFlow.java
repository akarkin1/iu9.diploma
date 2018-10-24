package ru.bmstu.schedule.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "study_flow", schema = "public", catalog = "schedule")
public class StudyFlow {
    private int id;
    private int enrollmentYear;
    private Collection<CalendarItem> calendarItems;
    private Specialization specialization;
    private Department department;
    private Collection<StudyGroup> studyGroups;

    @ManyToOne
    @JoinTable(
            name = "dep_to_spec",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Id
    @Column(name = "flow_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start_year", nullable = false)
    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyFlow studyFlow = (StudyFlow) o;
        return id == studyFlow.id &&
                enrollmentYear == studyFlow.enrollmentYear;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, enrollmentYear);
    }

    @OneToMany(mappedBy = "studyFlow")
    public Collection<CalendarItem> getCalendarItems() {
        return calendarItems;
    }

    public void setCalendarItems(Collection<CalendarItem> calendarItems) {
        this.calendarItems = calendarItems;
    }

    @ManyToOne
    @JoinTable(
            name = "dep_to_spec",
            joinColumns = @JoinColumn(name = "spec_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization spec) {
        this.specialization = spec;
    }

    @OneToMany(mappedBy = "studyFlow")
    public Collection<StudyGroup> getStudyGroups() {
        return studyGroups;
    }

    public void setStudyGroups(Collection<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }
}

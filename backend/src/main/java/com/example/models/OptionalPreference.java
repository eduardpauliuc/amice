package com.example.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "optional_preferences")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(OptionalPreferenceId.class)
public class OptionalPreference {

    @EmbeddedId
    private OptionalPreferenceId id;

    @ManyToOne
    @MapsId("student_id")
    private Student student;

    @ManyToOne
    @MapsId("course_id")
    private Course course;

    @Column(name = "rank", nullable = false)
    private Integer rank;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionalPreference that = (OptionalPreference) o;
        return id.equals(that.id) && student.equals(that.student) && course.equals(that.course) && rank.equals(that.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, course, rank);
    }
}

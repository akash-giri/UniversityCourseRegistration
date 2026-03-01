package org.example.model;

import java.time.LocalDateTime;

// Enrollment.java - THIS is what your enrollmentCourse should have been.
// One record = one student + one course + metadata about that relationship
public class Enrollment {
    private final String id;
    private final Student student;      // ONE student
    private final Course course;        // ONE course
    private final LocalDateTime enrolledAt;
    private EnrollmentStatus status;

    public Enrollment(String id, Student student, Course course) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.enrolledAt = LocalDateTime.now();
        this.status = EnrollmentStatus.ACTIVE;
    }

    public String getId() { return id; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public LocalDateTime getEnrolledAt() { return enrolledAt; }
    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Enrollment[%s]: %s enrolled in %s | Status: %s | At: %s",
                id, student.getName(), course.getName(), status, enrolledAt);
    }
}
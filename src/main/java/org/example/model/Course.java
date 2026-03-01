package org.example.model;

// Course.java - clean, single responsibility
public class Course {
    private final String id;
    private final String code;
    private final String name;
    private final String instructor;
    private final int enrollmentCapacity;

    public Course(String id, String code, String name, 
                  String instructor, int enrollmentCapacity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.enrollmentCapacity = enrollmentCapacity;
    }

    // Only getters - Course is immutable (data doesn't change mid-semester)
    public String getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getInstructor() { return instructor; }
    public int getEnrollmentCapacity() { return enrollmentCapacity; }

    @Override
    public String toString() {
        return String.format("[%s] %s - Instructor: %s (Capacity: %d)", 
                              code, name, instructor, enrollmentCapacity);
    }
}
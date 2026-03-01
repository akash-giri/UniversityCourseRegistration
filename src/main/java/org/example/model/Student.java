package org.example.model;

// Student.java - Student knows nothing about enrollment logic
// This fixes your circular dependency problem
public class Student {
    private final String id;
    private final String name;
    private final String email;

    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("Student[%s]: %s (%s)", id, name, email);
    }
}
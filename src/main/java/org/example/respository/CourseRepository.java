package org.example.respository;

import org.example.model.Course;
import java.util.*;
import java.util.stream.Collectors;

public class CourseRepository {
    private final Map<String, Course> courses = new HashMap<>();

    public void addCourse(Course course) {
        courses.put(course.getId(), course);
    }

    public List<Course> search(String query) {
        String lowerQuery = query.toLowerCase();
        return courses.values().stream()
                .filter(c -> c.getCode().toLowerCase().contains(lowerQuery) || 
                             c.getName().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    public Optional<Course> findById(String id) {
        return Optional.ofNullable(courses.get(id));
    }
}
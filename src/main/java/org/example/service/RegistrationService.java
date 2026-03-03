package org.example.service;

import org.example.model.*;
import org.example.repository.CourseRepository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationService {
    private final CourseRepository courseRepo;
    // Map Course ID -> List of Enrollments
    private final Map<String, List<Enrollment>> courseEnrollments = new ConcurrentHashMap<>();
    
    public RegistrationService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    // Thread-safe registration method
    public synchronized String registerStudent(Student student, String courseId) throws Exception {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new Exception("Course not found"));

        List<Enrollment> currentEnrollments = 
            courseEnrollments.computeIfAbsent(courseId, k -> new ArrayList<>());

        // Requirement: Prevent over-enrollment (Race Condition check)
        if (currentEnrollments.size() >= course.getEnrollmentCapacity()) {
            throw new Exception("Registration failed: Course is full.");
        }

        // Logic to prevent double registration
        boolean alreadyEnrolled = currentEnrollments.stream()
                .anyMatch(e -> e.getStudent().getId().equals(student.getId()));
        
        if (alreadyEnrolled) {
            throw new Exception("Student already registered for this course.");
        }

        Enrollment newEnrollment = new Enrollment(UUID.randomUUID().toString(), student, course);
        currentEnrollments.add(newEnrollment);
        
        return "Successfully registered for " + course.getName();
    }

    public List<Enrollment> getStudentSchedule(String studentId) {
        return courseEnrollments.values().stream()
                .flatMap(List::stream)
                .filter(e -> e.getStudent().getId().equals(studentId))
                .toList();
    }
}
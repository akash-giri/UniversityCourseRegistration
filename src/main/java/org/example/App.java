package org.example;

import org.example.model.*;
import org.example.repository.CourseRepository;
import org.example.service.RegistrationService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InterruptedException {
        // 1. Setup Data
        CourseRepository repository = new CourseRepository();
        // Course with only 2 seats!
        Course javaCourse = new Course("C1", "CS101", "Java Masterclass", "Dr. James", 2);
        repository.addCourse(javaCourse);

        RegistrationService service = new RegistrationService(repository);

        // 2. Setup Multi-threading tools
        int numberOfStudents = 10;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfStudents);
        // This latch makes sure all threads start at the exact same moment
        CountDownLatch latch = new CountDownLatch(1);

        System.out.println("--- Starting Registration Race for 2 Seats ---");

        for (int i = 1; i <= numberOfStudents; i++) {
            final String studentId = "S" + i;
            final String studentName = "Student_" + i;

            executor.submit(() -> {
                try {
                    latch.await(); // Wait for the signal
                    Student student = new Student(studentId, studentName, studentId + "@uni.edu");
                    String result = service.registerStudent(student, "C1");
                    System.out.println("[SUCCESS] " + studentName + ": " + result);
                } catch (Exception e) {
                    System.err.println("[FAILED] " + studentName + ": " + e.getMessage());
                }
            });
        }

        // 3. Fire the starting gun!
        latch.countDown();

        executor.shutdown();
        Thread.sleep(2000); // Wait for threads to finish

        // 4. Verify Final State
        System.out.println("\n--- Final Enrollment List ---");
        service.getStudentSchedule("S1"); // Internal check
        // In a real app, we'd count the enrollments in the service
    }
}
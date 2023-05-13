package builders;

import entities.Student;

import java.util.List;

public abstract class StudentsBuilder {

    public static List<Student> getAllStudents() {
        return List.of(
            new Student(1, "Aluno 1", 6.0f, 7.8f, 7.4f),
            new Student(2, "Aluno 2", 8.0f, 5.0f, 9.9f),
            new Student(3, "Aluno 3", 6.6f, 6.6f, 6.4f),
            new Student(4, "Aluno 4", 6.7f, 7.9f, 10.0f),
            new Student(5, "Aluno 5", 6.1f, 4.4f, 5.7f),
            new Student(6, "Aluno 6", 7.3f, 7.3f, 8.8f),
            new Student(7, "Aluno 7", 8.2f, 8.2f, 9.9f),
            new Student(8, "Aluno 8", 10.0f, 9.1f, 7.7f)
        );
    }
}

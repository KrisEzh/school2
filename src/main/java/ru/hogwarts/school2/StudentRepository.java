package ru.hogwarts.school2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Collection<Student> findByAgeBetween(int age, int age2);

    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    Integer getStudentsAmount();

    @Query(value = "SELECT AVG(age) from student", nativeQuery = true)
    Integer getAvgAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5",nativeQuery = true)
    List<Student> getLastFiveStudents();

}


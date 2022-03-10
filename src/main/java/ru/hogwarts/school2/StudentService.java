package ru.hogwarts.school2;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
    public Student findStudent(long id){
        return studentRepository.findById(id).get();
    }
    public Student editStudent(Student student){
        return studentRepository.save(student);
    }
    public Student deleteStudent(long id){
        return studentRepository.getById(id);
    }

    public Collection<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public List<Student> filterByAge(int age){
        return getAllStudents()
                .stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
    public Collection<Student> findByAgeBetween(int age, int age2){
        return studentRepository.findByAgeBetween(age, age2);
    }
    public Integer getStudentsAmount(){
        return studentRepository.getStudentsAmount();
    }

    public Integer getAvgAge() {
       return studentRepository.getAvgAge();
    }

    public List<Student> getLastFiveStudents() {
        return studentRepository.getLastFiveStudents();
    }
}

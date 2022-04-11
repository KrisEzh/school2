package ru.hogwarts.school2;

import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
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

    public double findMiddleAge(){
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge).average().getAsDouble();
    }
    public List<String> findStudentsStartsWithLetterV(){
        return studentRepository.findAll()
                .stream()
                .filter(student-> student.getName().startsWith("v"))
                .map((student)->student.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public void run(Long id) {
        System.out.println(studentRepository.getById(id));
    }

    public void printStudents() {
        run(62L);
        run(63L);

        new Thread(() -> {
            run(64L);
            run(65L);
        }).start();

        new Thread(() -> {
            run(66L);
            run(67L);
        }).start();
    }


    public void runSynchronized(Long id) {
        synchronized (getAllStudents()) {
            System.out.println(studentRepository.getById(id));
        }
    }

    public void printSynchronizedStudents() {
        runSynchronized(62L);
        runSynchronized(63L);

        new Thread(() -> {
            runSynchronized(64L);
            runSynchronized(65L);
        }).start();

        new Thread(() -> {
            runSynchronized(66L);
            runSynchronized(67L);
        }).start();
    }



}

package ru.hogwarts.school2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentsAmountController {

    private final StudentService studentService;

    public StudentsAmountController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students-amount")
    public Integer getStudentsAmount(){
        return studentService.getStudentsAmount();
    }

    @GetMapping("/students-avg")
    public Integer getAvgAge(){
        return studentService.getAvgAge();
    }
    @GetMapping("/students-last5")
    public List<Student> getLastFiveStudents(){
        return studentService.getLastFiveStudents();
    }
}

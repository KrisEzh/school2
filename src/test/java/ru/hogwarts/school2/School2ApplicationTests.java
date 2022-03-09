package ru.hogwarts.school2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Collection;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.hogwarts.school2.StudentTestConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class School2ApplicationTests {

    private Student student;

    private String baseUrl;

    private long studentId;

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void init(){
        student = new Student();
        student.setId(1L);
        student.setName("name");
        student.setAge(20);
    }

    @BeforeEach
    public void baseUrl(){
        this.baseUrl = "http://localhost:" +port + "/student/";
    }

    @AfterEach
    public void deleteTest(){
        restTemplate.delete(baseUrl+studentId);
    }

    @Test
    public void contextLoads(){
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testCreateStudent() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
                .isNotEmpty();
        Assertions
                .assertThat(this.restTemplate.postForObject(baseUrl,student,Student.class))
                .isEqualTo(student);
    }


    @Test
    public void testGetStudents()  {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotNull();
    }

    @Test
    public void testPostStudent() {
        student.setAge(8);
        studentId = restTemplate.postForObject(baseUrl,student,Student.class).getId();
        student.setId(studentId);
        restTemplate.put(baseUrl,student);

        Assertions
                .assertThat(this.restTemplate.getForObject(baseUrl + studentId, Student.class))
                .isEqualTo(student);
    }

    @Test
    public void testDeleteStudent() throws Exception {
        studentId = restTemplate.postForObject(baseUrl,student,Student.class).getId();
        student.setId(studentId);
        restTemplate.delete(baseUrl+studentId);

       Assertions
               .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student" + studentId,Student.class))
               .isNotIn(student);

    }
    @Test
    public void findByAgeBetweenTest() {
        student.setAge(AGE1);
        studentId = restTemplate.postForObject(baseUrl,student,Student.class).getId();
        student.setAge(AGE2);
        studentId = restTemplate.postForObject(baseUrl,student,Student.class).getId();
        student.setAge(AGE3);
        studentId = restTemplate.postForObject(baseUrl,student,Student.class).getId();

        Assertions
                .assertThat(this.restTemplate.getForObject(baseUrl + "?min=" + START_AGE +"?max=" +END_AGE, String.class))
                .isGreaterThan(String.valueOf(3));
    }


    @Test
    public void getByAgeTest(){
        student.setAge(BABY);
        studentId = restTemplate.postForObject(baseUrl,student, Student.class).getId();
        student.setId(studentId);

        Assertions
                .assertThat(this.restTemplate.getForObject(baseUrl+ "?age=" + BABY, String.class))
                .isNotNull();
    }

}


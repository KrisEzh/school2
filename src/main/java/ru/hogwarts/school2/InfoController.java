package ru.hogwarts.school2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InfoController {

    @Value("${server.port}")
    private int value;

    @GetMapping(path="/getPort")
    public Integer getPort (){
        return value;
    }
}

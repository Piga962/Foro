package foro.Hub.controller;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xd")
public class HelloController {

    @GetMapping
    public String hello(){
        return "Hola xd si funciona un poco";
    }
}

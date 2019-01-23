package com.agung.MahasiswaCrudSpring.controller;


        import org.springframework.web.bind.annotation.*;
        import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class MainController {

    @GetMapping(value = "/")
    public String home(){

        return "Belajar Spring Boot";
    }

}

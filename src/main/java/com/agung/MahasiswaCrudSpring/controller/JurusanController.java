package com.agung.MahasiswaCrudSpring.controller;


import com.agung.MahasiswaCrudSpring.entity.Fakultas;
import com.agung.MahasiswaCrudSpring.entity.Jurusan;
import com.agung.MahasiswaCrudSpring.service.JurusanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class JurusanController {
    private final JurusanService service;


    @Autowired
    public JurusanController(JurusanService service) {
        this.service = service;
    }

    @GetMapping(value = "/jurusan")
    public List<Jurusan> jurusan(){
        return service.find();
    }

    @GetMapping(value = "/jurusan/{id}")
    public Jurusan findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }


    @PostMapping(value = "/jurusan")
    public String save(@RequestBody Jurusan jurusan) {
        Jurusan data = service.save(jurusan);
        if (data.getId() == 0) {
            return "Gagal insert data";
        } else {
            return "Insert data berhasil";
        }
    }

    @PutMapping(value = "/jurusan")
    public String update(@RequestBody Jurusan jurusan) {
        Jurusan data = service.update(jurusan);
        if (data.getId() == 0) {
            return "Gagal update data";
        } else {
            return "Update data berhasil";
        }
    }

    @DeleteMapping(value = "/jurusan/{id}")
    public String delete(@PathVariable("id") Integer id) {
        int data = service.delete(new Jurusan(id));
        if (data == 0) {
            return "Gagal delete data";
        } else {
            return "Delete data berhasil";
        }
    }

}

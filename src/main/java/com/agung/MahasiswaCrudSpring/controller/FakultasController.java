package com.agung.MahasiswaCrudSpring.controller;


import com.agung.MahasiswaCrudSpring.entity.Fakultas;
import com.agung.MahasiswaCrudSpring.entity.Matakuliah;
import com.agung.MahasiswaCrudSpring.service.FakultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FakultasController {
    private final FakultasService service;


    @Autowired
    public FakultasController(FakultasService service) {
        this.service = service;
    }

    @GetMapping(value = "/fakultas")
    public List<Fakultas> fakultas() {
        return service.find();
    }

    @GetMapping(value = "/fakultas/{id}")
    public Fakultas findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @PostMapping(value = "/fakultas")
    public String save(@RequestBody Fakultas fakultas) {
        Fakultas data = service.save(fakultas);
        if (data.getId() == 0) {
            return "Gagal insert data";
        } else {
            return "Insert data berhasil";
        }
    }

    @PutMapping(value = "/fakultas")
    public String update(@RequestBody Fakultas fakultas) {
        Fakultas data = service.update(fakultas);
        if (data.getId() == 0) {
            return "Gagal update data";
        } else {
            return "Update data berhasil";
        }
    }

    @DeleteMapping(value = "/fakultas/{id}")
    public String delete(@PathVariable("id") Integer id) {
        int data = service.delete(new Fakultas(id));
        if (data == 0) {
            return "Gagal delete data";
        } else {
            return "Delete data berhasil";
        }
    }
}


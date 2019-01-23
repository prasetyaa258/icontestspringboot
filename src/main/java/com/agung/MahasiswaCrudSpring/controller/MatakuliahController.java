package com.agung.MahasiswaCrudSpring.controller;

import com.agung.MahasiswaCrudSpring.entity.Fakultas;
import com.agung.MahasiswaCrudSpring.entity.Matakuliah;
import com.agung.MahasiswaCrudSpring.service.MatakuliahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatakuliahController {

    @Autowired
    private MatakuliahService service;

    @GetMapping(value = "/matakuliah")
    public List<Matakuliah> matakuliah(){
        return service.find();
    }

    @GetMapping(value = "/matakuliah/{id}")
    public Matakuliah findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @PostMapping(value = "/matakuliah")
    public String save(@RequestBody Matakuliah matakuliah){
        Matakuliah data = service.save(matakuliah);
        if (data.getId()==0) {
            return "Gagal Insert";
        }else {
            return "Insert data berhasil";
        }
    }

    @PutMapping(value = "/matakuliah")
    public String update(@RequestBody Matakuliah matakuliah) {
        Matakuliah data = service.update(matakuliah);
        if (data.getId() == 0) {
            return "Gagal update data";
        } else {
            return "Update data  berhasil";
        }
    }

    @DeleteMapping(value = "/matakuliah/{id}")
    public String delete(@PathVariable("id") Integer id) {
        int data = service.delete(new Matakuliah(id));
        if (data == 0) {
            return "Gagal delete data";
        } else {
            return "Delete data berhasil";
        }



    }
}




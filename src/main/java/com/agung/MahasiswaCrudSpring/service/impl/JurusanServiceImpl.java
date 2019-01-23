package com.agung.MahasiswaCrudSpring.service.impl;


import com.agung.MahasiswaCrudSpring.dao.FakultasDAO;
import com.agung.MahasiswaCrudSpring.dao.JurusanDAO;
import com.agung.MahasiswaCrudSpring.entity.Fakultas;
import com.agung.MahasiswaCrudSpring.entity.Jurusan;
import com.agung.MahasiswaCrudSpring.service.JurusanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JurusanServiceImpl implements JurusanService {


    @Autowired
    private JurusanDAO jurusanDAO;

//    //tambah
//    @Autowired
//    private FakultasDAO fakultasDAO;



    @Override
    public Jurusan save(Jurusan param) {
        return jurusanDAO.save(param);
//        return fakultasDAO.save(param); //tambah
    }

    @Override
    public Jurusan update(Jurusan param) {
        return jurusanDAO.update(param);
    }

    @Override
    public int delete(Jurusan param) {
        return jurusanDAO.delete(param);
    }



    @Override
    public List<Jurusan> find(){
       return jurusanDAO.find();
    }

    @Override
    public Jurusan findById(int id) {
        return jurusanDAO.findById(id);
    }



}
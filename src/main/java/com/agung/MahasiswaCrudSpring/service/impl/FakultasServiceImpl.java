package com.agung.MahasiswaCrudSpring.service.impl;


import com.agung.MahasiswaCrudSpring.dao.FakultasDAO;
import com.agung.MahasiswaCrudSpring.entity.Fakultas;
import com.agung.MahasiswaCrudSpring.service.FakultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FakultasServiceImpl  implements FakultasService{

    @Autowired
    private FakultasDAO fakultasDAO;

    @Override
    public Fakultas save (Fakultas param){
        return  fakultasDAO.save(param);
    }


    @Override
    public Fakultas update(Fakultas param){

       return  fakultasDAO.update(param);
    }

    @Override
    public int delete(Fakultas param){

        return fakultasDAO.delete(param);
    }

    @Override
    public List<Fakultas> find(){

        return fakultasDAO.find();
    }

    @Override
    public Fakultas findById(int id) {
        return fakultasDAO.findById(id);
    }


}

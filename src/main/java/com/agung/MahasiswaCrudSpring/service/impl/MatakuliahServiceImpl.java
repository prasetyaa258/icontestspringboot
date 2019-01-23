package com.agung.MahasiswaCrudSpring.service.impl;


import com.agung.MahasiswaCrudSpring.dao.MatakuliahDAO;
import com.agung.MahasiswaCrudSpring.entity.Matakuliah;

import com.agung.MahasiswaCrudSpring.service.MatakuliahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatakuliahServiceImpl implements MatakuliahService {

    @Autowired
    private MatakuliahDAO matakuliahDAO;

    @Override
    public Matakuliah save(Matakuliah param) {
        return matakuliahDAO.save(param);
    }

    @Override
    public Matakuliah update(Matakuliah param) {
        return matakuliahDAO.update(param);
    }

    @Override
    public int delete(Matakuliah param) {
        return matakuliahDAO.delete(param);
    }

    @Override
    public List<Matakuliah> find() {
        return matakuliahDAO.find();
    }

    @Override
    public Matakuliah findById(int id) {
        return matakuliahDAO.findById(id);
    }
}

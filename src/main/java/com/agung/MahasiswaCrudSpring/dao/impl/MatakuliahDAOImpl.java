package com.agung.MahasiswaCrudSpring.dao.impl;

import com.agung.MahasiswaCrudSpring.common.Table;
import com.agung.MahasiswaCrudSpring.dao.MatakuliahDAO;
import com.agung.MahasiswaCrudSpring.entity.Matakuliah;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class MatakuliahDAOImpl implements MatakuliahDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Matakuliah save(Matakuliah param) {
        String sql = "INSERT INTO " + Table.TABLE_MATAKULIAH + "(nama, sks) VALUES (?,?)";

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getNama());
            ps.setString(2, param.getSks());
            return ps;
        }, keyHolder);

        param.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return param;
    }

    @Override
    public Matakuliah update(Matakuliah param) {
        String sql = "UPDATE " + Table.TABLE_MATAKULIAH + " SET " +
                "nama = ?, " +
                "sks = ? " +
                "WHERE id =  ? ";

        jdbcTemplate.update(sql,
                param.getNama(),
                param.getSks(),
                param.getId());

        return param;
    }

    @Override
    public int delete(Matakuliah param) {
        String sql = "DELETE FROM " + Table.TABLE_MATAKULIAH + " WHERE id = ? ";

        final int delete = jdbcTemplate.update(sql, param.getId());
        return delete;
    }

    @Override
    public List<Matakuliah> find() {
        String sql = "SELECT * FROM " + Table.TABLE_MATAKULIAH;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Matakuliah.class));
    }

    @Override
    public Matakuliah findById(int id) {
        String sql = "SELECT * FROM " + Table.TABLE_MATAKULIAH + " WHERE id = ? ";

        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Matakuliah.class), id);
        } catch (EmptyResultDataAccessException ignored) {
        }

        return null;
    }


}

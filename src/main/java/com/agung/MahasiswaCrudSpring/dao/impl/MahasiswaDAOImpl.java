package com.agung.MahasiswaCrudSpring.dao.impl;


import com.agung.MahasiswaCrudSpring.common.Table;
import com.agung.MahasiswaCrudSpring.dao.MahasiswaDAO;
import com.agung.MahasiswaCrudSpring.entity.Fakultas;
import com.agung.MahasiswaCrudSpring.entity.Jurusan;
import com.agung.MahasiswaCrudSpring.entity.Mahasiswa;
import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class MahasiswaDAOImpl implements MahasiswaDAO {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Mahasiswa save(Mahasiswa param) {
        String sql = "INSERT INTO " + Table.TABLE_MAHASISWA+ " ( nama, tempat,tanggal ,alamat, ayah, ibu,idJurusan) VALUES (?,?,?,?,?,?,?)";

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getNama());
            ps.setString(2, param.getTempat());
            ps.setDate(3, param.getTanggal());
            ps.setString(4, param.getAlamat());
            ps.setString(5, param.getAyah());
            ps.setString(6, param.getIbu());
            ps.setInt(7, param.getIdJurusan());

            return ps;
        }, keyHolder);

        param.setId(Objects.requireNonNull(keyHolder.getKey().intValue()));
        return param;
    }

    @Override
    public Mahasiswa update(Mahasiswa param) {

        String sql= "UPDATE " + Table.TABLE_MAHASISWA + " SET "+
                "nama = ?, tempat = ?, tanggal = ?, alamat = ?," +
                "ayah = ?, ibu = ?, idJurusan = ? WHERE id = ?";

        jdbcTemplate.update(sql,
                param.getNama(),
                param.getTempat(),
                param.getTanggal(),
                param.getAlamat(),
                param.getAyah(),
                param.getIbu(),
                param.getIdJurusan(),
                param.getId());
        return param;
    }

    @Override
    public int delete(Mahasiswa param) {
        return 0;
    }

    @Override
    public List<Mahasiswa> find() {
        String sql = "SELECT " +
                "mahasiswa.id AS id, " +
                "mahasiswa.nama AS nama, " +
                "mahasiswa.tempat AS tempat, " +
                "mahasiswa.tanggal AS tanggal, " +
                "mahasiswa.alamat AS alamat, " +
                "mahasiswa.ayah AS ayah, " +
                "mahasiswa.ibu AS ibu, " +
                "jurusan.id AS idJurusan, " +
                "jurusan.nama AS namaJurusan, " +
                "fakultas.id AS idFakultas, " +
                "fakultas.nama AS namaFakultas " +
                "FROM " + Table.TABLE_MAHASISWA + " mahasiswa INNER JOIN " + Table.TABLE_JURUSAN +
                " jurusan ON jurusan.id = mahasiswa.idJurusan " +
                "INNER JOIN  " + Table.TABLE_FAKULTAS + " fakultas ON fakultas.id = jurusan.idFakultas ";



        //ganti imi
        return jdbcTemplate.query(sql, new RowMapper<Mahasiswa>() {
            @Override
            public Mahasiswa mapRow(ResultSet rs, int rowNum) throws SQLException {
                Mahasiswa mahasiswa=new Mahasiswa();

                mahasiswa.setId(rs.getInt("id"));
                mahasiswa.setNama(rs.getString("nama"));
                mahasiswa.setTempat(rs.getString("tempat"));
                mahasiswa.setTanggal(rs.getDate("tanggal"));
                mahasiswa.setAlamat(rs.getString("alamat"));
                mahasiswa.setAyah(rs.getString("ayah"));
                mahasiswa.setIbu(rs.getString("ibu"));
                mahasiswa.setIdJurusan(rs.getInt("idJurusan") );

                Jurusan jurusan=new Jurusan();
                jurusan.setId(rs.getInt("idJurusan")); //nama_fakultas
                jurusan.setNama(rs.getString("namaJurusan"));
                jurusan.setIdFakultas(rs.getInt("idFakultas"));

                Fakultas fakultas=new Fakultas();
                fakultas.setId(rs.getInt("idFakultas"));
                fakultas.setNama(rs.getString("namaFakultas"));

                mahasiswa.setFakultas(fakultas);
                mahasiswa.setJurusan(jurusan);

                return mahasiswa;

            }
        });//sampe sini
    }

    @Override
    public Mahasiswa findById(int id) {
        String sql = "SELECT * FROM " + Table.TABLE_MAHASISWA + " WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Mahasiswa.class), id);
        } catch (EmptyResultDataAccessException ignored) {
        }

        return null;
    }


}

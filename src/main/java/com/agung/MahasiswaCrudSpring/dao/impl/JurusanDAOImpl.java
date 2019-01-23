package com.agung.MahasiswaCrudSpring.dao.impl;

        import com.agung.MahasiswaCrudSpring.common.Table;
        import com.agung.MahasiswaCrudSpring.dao.JurusanDAO;
        import com.agung.MahasiswaCrudSpring.entity.Fakultas;
        import com.agung.MahasiswaCrudSpring.entity.Jurusan;
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
public class JurusanDAOImpl implements JurusanDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Jurusan save(Jurusan param) {
        String sql = "INSERT INTO " + Table.TABLE_JURUSAN + " (nama, idFakultas) VALUES (?,?)";

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getNama());
            ps.setInt(2, param.getIdFakultas());
            return ps;
        }, keyHolder);
        param.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return param;
    }

    @Override
    public Jurusan update(Jurusan param) {
        String sql= "UPDATE " + Table.TABLE_JURUSAN +  " SET "+
                "nama = ?, idFakultas = ? WHERE id = ?";

        jdbcTemplate.update(sql,
                param.getNama(),
                param.getIdFakultas(),
                param.getId());
        return param;
    }

    @Override
    public int delete(Jurusan param) {
        String sql= "DELETE FROM " + Table.TABLE_JURUSAN  + " WHERE id = ? ";
        final int delete = jdbcTemplate.update(sql, param.getId());
        return delete;
    }

    @Override
    public List<Jurusan> find() {
        String sql = "SELECT " +
                "jurusan.id AS id, " +
                "fakultas.id AS idFakultas, " +
                "jurusan.nama AS nama, " +
                "fakultas.nama AS namaFakultas " +
                "FROM " + Table.TABLE_JURUSAN + " jurusan INNER JOIN " + Table.TABLE_FAKULTAS +
                " fakultas ON jurusan.idFakultas = fakultas.id";



    //ganti imi
        return jdbcTemplate.query(sql, new RowMapper<Jurusan>() {
            //rowmapper menyamakan antara variabel dan database
            @Override
            public Jurusan mapRow(ResultSet rs, int rowNum) throws SQLException {
               Jurusan jurusan=new Jurusan();
               jurusan.setId(rs.getInt("id"));
               jurusan.setNama(rs.getString("nama"));
               jurusan.setIdFakultas(rs.getInt("idFakultas"));


               Fakultas fakultas=new Fakultas();
               fakultas.setId(rs.getInt("idFakultas"));
               fakultas.setNama(rs.getString("namaFakultas"));
               jurusan.setFakultas(fakultas);
                return jurusan;
            }
        });//sampe sini
    }

    @Override
    public Jurusan findById(int id) {
        String sql = "SELECT * FROM " + Table.TABLE_JURUSAN + " WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Jurusan.class), id);
        } catch (EmptyResultDataAccessException ignored) {
        }

        return null;
    }
    }





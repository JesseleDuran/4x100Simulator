package daos.impl;

import daos.JDBCEquipoDAO;
import models.Equipo;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JDBCEquipoDAOImpl implements JDBCEquipoDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Equipo equipo){

        String sql = "INSERT INTO equipo " +
                "(nombre) VALUES (?)";

        jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(sql, new Object[] {
                equipo.getNombre()
        });
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    public Equipo findById(int id){

        String sql = "SELECT * FROM equipo WHERE ID = ?";

        jdbcTemplate = new JdbcTemplate(dataSource);
        Equipo equipo = (Equipo) jdbcTemplate.queryForObject(
                sql, new Object[] { id }, new BeanPropertyRowMapper(Equipo.class));

        return equipo;
    }

    @SuppressWarnings("rawtypes")
    public List<Equipo> findAll(){

        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM equipo";

        List<Equipo> equipos = new ArrayList<Equipo>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Equipo equipo = new Equipo();
            equipo.setId(Integer.parseInt(String.valueOf(row.get("ID"))));
            equipo.setNombre((String)row.get("nombre"));
            equipos.add(equipo);
        }

        return equipos;
    }

    @Override
    public String findNameById(int id) {
        String sql = "SELECT nombre FROM equipo WHERE ID = ?";

        String nombre = (String)jdbcTemplate.queryForObject(
                sql, new Object[] { id }, String.class);

        return nombre;
    }

    @Override
    public void insertBatch1(final List<Equipo> equipos){

        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO equipo " +
                "(NAME) VALUES (?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Equipo equipo = equipos.get(i);
                ps.setLong(1, equipo.getId());
                ps.setString(2, equipo.getNombre());

            }

            public int getBatchSize() {
                return equipos.size();
            }
        });
    }

    @Override
    public void insertBatch2(final String sql){
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.batchUpdate(new String[]{sql});

    }

}

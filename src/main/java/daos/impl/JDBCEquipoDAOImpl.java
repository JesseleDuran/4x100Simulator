package daos.impl;

import daos.JDBCEquipoDAO;
import models.Corredor;
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
                equipo.getNombre_equipo()
        });
    }

    @Override
    @SuppressWarnings({ "unchecked" })
    public Equipo findById(int id){

        String sql = "SELECT * FROM equipo WHERE id = ?";

        jdbcTemplate = new JdbcTemplate(dataSource);
        Equipo equipo = (Equipo) jdbcTemplate.queryForObject(
                sql, new Object[] { id }, new BeanPropertyRowMapper(Equipo.class));

        return equipo;
    }

    @SuppressWarnings("rawtypes")
    public List<Map<String, Object>> findAll(){

        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM equipo INNER JOIN corredor ON (corredor.id_equipo = equipo.id)";

        List<Equipo> equipos = new ArrayList<Equipo>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        List<Corredor> corredores = new ArrayList<Corredor>();
        for (Map row : rows) {
            Equipo equipo = new Equipo();
            equipo.setId_equipo(Integer.parseInt(String.valueOf(row.get("id_equipo"))));
            equipo.setNombre_equipo((String)row.get("nombre_equipo"));
            //if (corredor.id_equipo = equipo.equipo_id)
            equipos.add(equipo);
        }

        return rows;
    }

    @Override
    public String findNameById(int id) {
        String sql = "SELECT nombre_equipo FROM equipo WHERE id = ?";

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
                ps.setLong(1, equipo.getId_equipo());
                ps.setString(2, equipo.getNombre_equipo());

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

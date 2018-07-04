package daos.impl;

import daos.JDBCCarreraDAO;
import models.Carrera;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class JDBCCarreraDAOImpl implements JDBCCarreraDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Map<String, Object>> findAll() {

            jdbcTemplate = new JdbcTemplate(dataSource);
            String sql = "SELECT e1.nombre_equipo as primer_lugar, e2.nombre_equipo AS segundo_lugar, e3.nombre_equipo AS tercer_lugar, carrera.nombre_carrera, carrera.tiempo_total, carrera.fecha FROM `carrera` \n" +
                    "JOIN equipo e1 ON (carrera.id_equipo_ganador1 = e1.id)\n" +
                    "JOIN equipo e2 ON (carrera.id_equipo_ganador2 = e2.id)\n" +
                    "JOIN equipo e3 ON (carrera.id_equipo_ganador3 = e3.id)";

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

            return rows;

    }

    @Override
    public void insert(Carrera carrera) {

        String sql = "INSERT INTO carrera " +
                "(nombre_carrera, tiempo_total, fecha, id_equipo_ganador1, id_equipo_ganador2, id_equipo_ganador3) VALUES (?)";

        jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(sql, new Object[] {
                carrera.getNombre_carrera(),
                carrera.getTiempo_total(),
                carrera.getFecha(),
                carrera.getEquipo_ganador1(),
                carrera.getEquipo_ganador2(),
                carrera.getEquipo_ganador3()
        });

    }


}

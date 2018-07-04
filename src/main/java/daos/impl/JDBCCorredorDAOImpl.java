package daos.impl;

import daos.JDBCCorredorDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class JDBCCorredorDAOImpl implements JDBCCorredorDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Map<String, Object>> findAll() {

        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM `corredor` JOIN equipo " +
                "ON (corredor.id_equipo = equipo.id) " +
                "ORDER BY `equipo`.`nombre_equipo` ASC";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        return rows;

    }
}

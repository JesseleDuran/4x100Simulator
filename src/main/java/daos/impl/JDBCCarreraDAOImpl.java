package daos.impl;

import daos.JDBCCarreraDAO;
import models.Carrera;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
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
                "(nombre_carrera, fecha, id_equipo_ganador1, id_equipo_ganador2, id_equipo_ganador3) VALUES (?,?,?,?,?)";

        jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(sql, new Object[] {
                carrera.getNombre_carrera(),
                carrera.getFecha(),
                carrera.getId_equipo_ganador1(),
                carrera.getId_equipo_ganador2(),
                carrera.getId_equipo_ganador3()
        });

    }

    //TODO: refactor
    @Override
    public int insertReturningId(Carrera carrera) {
        String URL="localhost:3306";
        String USERNAME="jessele";
        String PASSWORD="";
        String DATABASE="race_simulator";

        int id=0;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://"+ URL + "/" + DATABASE,USERNAME,PASSWORD);

            PreparedStatement ps=con.prepareStatement("INSERT INTO carrera " +
                    "(nombre_carrera, fecha, id_equipo_ganador1, id_equipo_ganador2, id_equipo_ganador3) VALUES (?,?,?,?,?)" ,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, carrera.getNombre_carrera());
            ps.setString(2, carrera.getFecha());
            ps.setInt(3, carrera.getId_equipo_ganador1());
            ps.setInt(4, carrera.getId_equipo_ganador2());
            ps.setInt(5, carrera.getId_equipo_ganador3());

            ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                id=rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }


}

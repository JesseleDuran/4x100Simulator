package daos.impl;

import daos.JDBCCarreraDAO;
import models.Carrera;
import models.CarreraCorredor;
import models.CarreraEquipo;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
            String sql = "SELECT e1.nombre_equipo as primer_lugar, e2.nombre_equipo AS segundo_lugar, e3.nombre_equipo AS tercer_lugar, carrera.nombre_carrera, carrera.fecha FROM `carrera` \n" +
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

    @Override
    public void insertBatchCarreraEquipo(final List<CarreraEquipo> carreraEquipos){

        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO carrera_equipo " +
                "(id_carrera, id_equipo, tiempo_equipo, n_posicion) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CarreraEquipo carreraEquipo = carreraEquipos.get(i);
                ps.setLong(1, carreraEquipo.getId_carrera());
                ps.setLong(2, carreraEquipo.getId_equipo());
                ps.setString(3, carreraEquipo.getTiempo_equipo());
                ps.setInt(4, carreraEquipo.getN_posicion());

            }

            public int getBatchSize() {
                return carreraEquipos.size();
            }
        });
    }

    @Override
    public void insertBatchCarreraCorredor(final List<CarreraCorredor> carreraCorredores){

        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO carrera_corredor " +
                "(id_carrera, id_corredor, tiempo_fase) VALUES (?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CarreraCorredor carreraCorredor = carreraCorredores.get(i);
                ps.setLong(1, carreraCorredor.getId_carrera());
                ps.setLong(2, carreraCorredor.getId_corredor());
                ps.setString(3, carreraCorredor.getTiempo_fase());

            }

            public int getBatchSize() {
                return carreraCorredores.size();
            }
        });
    }


}

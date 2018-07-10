package daos;

import models.Equipo;

import java.util.List;
import java.util.Map;

public interface JDBCEquipoDAO {

    public void insert(Equipo equipo);
    public Equipo findById(int id);
    public List<Map<String, Object>> findAll();
    public String findNameById(int id);
    public void insertBatch1(final List<Equipo> equipos);
    public void insertBatch2(final String sql);

}

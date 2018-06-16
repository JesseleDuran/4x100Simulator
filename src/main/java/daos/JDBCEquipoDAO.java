package daos;

import models.Equipo;

import java.util.List;

public interface JDBCEquipoDAO {

    public void insert(Equipo equipo);
    public Equipo findById(int id);
    public List<Equipo> findAll();
    public String findNameById(int id);
    public void insertBatch1(final List<Equipo> equipos);
    public void insertBatch2(final String sql);

}

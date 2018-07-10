package daos;

import models.Carrera;
import models.CarreraCorredor;
import models.CarreraEquipo;

import java.util.List;
import java.util.Map;

public interface JDBCCarreraDAO {

    public List<Map<String, Object>> findAll();
    public void insert(Carrera carrera);
    public void insertCarreraEquipo(CarreraEquipo carreraEquipo);
    public void insertCarreraCorredor(CarreraCorredor carreraCorredor);
    public int insertReturningId(Carrera carrera);
    public void insertBatchCarreraEquipo(final List<CarreraEquipo> carreraEquipos);
    public void insertBatchCarreraCorredor(final List<CarreraCorredor> carreraCorredores);
}

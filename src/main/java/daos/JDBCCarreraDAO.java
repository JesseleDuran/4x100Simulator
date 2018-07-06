package daos;

import models.Carrera;

import java.util.List;
import java.util.Map;

public interface JDBCCarreraDAO {

    public List<Map<String, Object>> findAll();
    public void insert(Carrera carrera);
    public int insertReturningId(Carrera carrera);
}

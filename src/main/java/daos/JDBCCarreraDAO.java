package daos;

import java.util.List;
import java.util.Map;

public interface JDBCCarreraDAO {

    public List<Map<String, Object>> findAll();
}

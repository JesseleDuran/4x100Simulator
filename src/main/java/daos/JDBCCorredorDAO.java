package daos;

import java.util.List;
import java.util.Map;

public interface JDBCCorredorDAO {

    public List<Map<String, Object>> findAll();
}

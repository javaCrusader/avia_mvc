package mappers;

import model.Aircraft;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;


@Service
public class AircraftRSExtractor implements ResultSetExtractor<Aircraft> {

    @Override
    public Aircraft extractData(ResultSet rs) throws SQLException {
        Aircraft item = new Aircraft();
        item.setId(rs.getInt("id"));
        item.setName(rs.getString("name"));
        return item;
    }
}
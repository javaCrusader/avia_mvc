package dao;

import model.Aircraft;
import mappers.AircraftRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class AircraftDaoImpl implements AircraftDao {

    @Autowired
    private AircraftRowMapper rowMapper;

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public AircraftDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        create();
    }


    private void create() {
        System.out.println("creation");
        jdbcTemplate.execute("create table IF NOT EXISTS aircrafts (id INTEGER NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "capacity int," +
                "PRIMARY KEY (id));");
    }

    @Override
    public boolean insert(Aircraft item) {
        String query = "insert into aircrafts (name, capacity) values (?,?)";

        int out = jdbcTemplate.update(query, preparedStatement -> {
            preparedStatement.setString(1, item.getName());
        });

        if (out != 0) {
            System.out.println("item saved");
            return true;
        } else {
            System.out.println("item save failed");
            return false;
        }
    }

    @Override
    public Aircraft get(int id) {
        String query = "select id,name,count from aircrafts where id=?"; //mapper make us to get id, comrade
        return jdbcTemplate.queryForObject(query, rowMapper, id);
    }

    @Override
    public boolean delete(int id) {

        String query = "delete from aircrafts where id=?";

        int out = jdbcTemplate.update(query, id);
        if (out != 0) {
            System.out.println("delete ok");
            return true;
        } else {
            System.out.println("delete failed");
            return false;
        }
    }

    @Override
    public List<Aircraft> getByName(String name) {
        String query = "select id, name, capacity from aircrafts where name=?";
        return jdbcTemplate.query(query, new Object[]{name}, rowMapper);
    }

    @Override
    public boolean update(Aircraft item) {
        String query = "update aircrafts set name=?, capacity=? where id=?";

        int out = jdbcTemplate.update(query, preparedStatement -> {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setInt(3, item.getId());
        });
        if (out != 0) {
            System.out.println("item updated");
            return true;
        } else {
            System.out.println("failed to update item");
            return false;
        }
    }

    @Override
    public List<Aircraft> getAll() {
        String query = "select id, name, capacity from aircrafts order by id";
        return jdbcTemplate.query(query, rowMapper);
    }
}

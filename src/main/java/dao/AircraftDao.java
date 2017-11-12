package dao;

import model.Aircraft;

import java.util.List;

public interface AircraftDao {

    boolean insert(Aircraft item);

    Aircraft get(int id);

    List<Aircraft> getByName(String name);

    boolean update(Aircraft item);

    boolean delete(int id);

    List<Aircraft> getAll();
}

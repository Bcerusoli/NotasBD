package Carrera;

import java.sql.SQLException;
import java.util.List;

public interface CarreraINT {
    CarreraDTO finByIdCarrera(int idCarrera) throws SQLException;
    List<CarreraDTO> findAll();
    void registrarCarrera(CarreraDTO carrera);
    void modificarCarrera(CarreraDTO carrera);
    void eliminarCarrera(CarreraDTO carrera);

}

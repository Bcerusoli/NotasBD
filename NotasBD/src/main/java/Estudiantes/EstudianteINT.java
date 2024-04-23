package Estudiantes;

import java.sql.SQLException;
import java.util.List;

public interface EstudianteINT {
    EstudianteDTO finByIdEstudianteDTO(int EstudianteID) throws SQLException;
    List<EstudianteDTO> findAll();
    void registrarEstudiante(EstudianteDTO estudiante);
    void modificarEstudiante(EstudianteDTO estudiante);
    void eliminarEstudiante(EstudianteDTO estudiante);
}

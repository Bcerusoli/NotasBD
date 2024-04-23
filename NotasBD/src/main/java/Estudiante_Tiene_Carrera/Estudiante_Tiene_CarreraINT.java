package Estudiante_Tiene_Carrera;

import java.util.List;

public interface Estudiante_Tiene_CarreraINT {
    Estudiante_Tiene_CarreraDTO finByIdNota_Tiene_Carrera(int Estudiante_Tiene_Carrera_id) throws Exception;
    List<Estudiante_Tiene_CarreraDTO> findAll();
    void registrarNota_Tiene_Carrera(Estudiante_Tiene_CarreraDTO EstudianteCarrera);
    void modificarNota_Tiene_Carrera(Estudiante_Tiene_CarreraDTO EstudianteCarrera);
    void eliminarNota_Tiene_Carrera(Estudiante_Tiene_CarreraDTO EstudianteCarrera);
}

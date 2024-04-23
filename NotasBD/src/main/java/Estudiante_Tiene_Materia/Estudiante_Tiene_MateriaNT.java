package Estudiante_Tiene_Materia;

import java.sql.SQLException;
import java.util.List;

public interface Estudiante_Tiene_MateriaNT {
    Estudiante_Tiene_MateriaDTO finById(int  Estudiante_Tiene_Materia_id) throws SQLException;
    List<Estudiante_Tiene_MateriaDTO> findAll();
    void registrar(Estudiante_Tiene_MateriaDTO estudiantemateria);
    void modificar(Estudiante_Tiene_MateriaDTO estudiantemateria);
    void eliminar(Estudiante_Tiene_MateriaDTO estudiantemateria);
}

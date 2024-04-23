package Materia;

import java.sql.SQLException;
import java.util.List;

public interface MateriaINT {
    MateriaDTO finByIdMateriaDTO(int idMateria) throws SQLException;
    List<MateriaDTO> findAll();
    void registrarMateria(MateriaDTO materia);
    void modificarMateria(MateriaDTO materia);
    void eliminarMateria(MateriaDTO materia);
}

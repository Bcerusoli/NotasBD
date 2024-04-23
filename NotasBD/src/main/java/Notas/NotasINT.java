package Notas;

import java.util.List;

public interface NotasINT {
    NotasDTO finByIdNota(int idNota) throws Exception;
    List<NotasDTO> findAll();
    void registrarNota(NotasDTO nota);
    void modificarNota(NotasDTO nota);
    void eliminarNota(NotasDTO nota);

}

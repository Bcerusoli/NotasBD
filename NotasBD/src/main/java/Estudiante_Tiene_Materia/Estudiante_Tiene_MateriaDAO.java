package Estudiante_Tiene_Materia;

import Estudiante_Tiene_Materia.Estudiante_Tiene_MateriaDTO;
import Materia.MateriaDTO;
import Modelo.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Estudiante_Tiene_MateriaDAO {
    private List<Estudiante_Tiene_MateriaDTO> estudianteMateria = new ArrayList<>();
    private Conexion conexion = Conexion.getInstance();


    public Estudiante_Tiene_MateriaDTO findByIdEstudiante_Tiene_MateriaDTO(int estudiante_Tiene_Materia_id) throws SQLException {
        String sqlQuery = "SELECT * FROM Estudiante_Tiene_Materia WHERE Estudiante_Tiene_Materia_id = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, estudiante_Tiene_Materia_id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Estudiante_Tiene_MateriaDTO estudianteMateria = new Estudiante_Tiene_MateriaDTO();
                    estudianteMateria.setEstudiante_Tiene_Materia_id(rs.getInt("Estudiante_Tiene_Materia_id"));
                    estudianteMateria.setEstudianteID(rs.getInt("Estudiante_id"));
                    estudianteMateria.setIdMateria(rs.getInt("Materia_id"));
                    return estudianteMateria;
                }
            }
        }
        return null;
    }

    public void registrarEstudiante_Tiene_Materia(Estudiante_Tiene_MateriaDTO estudianteMateria) {
        String sqlQuery = "INSERT INTO Estudiante_Tiene_Materia (Estudiante_id, Materia_id) VALUES (?, ?)";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement insertar = connection.prepareStatement(sqlQuery)) {
            insertar.setInt(1, estudianteMateria.getEstudianteID());
            insertar.setInt(2, estudianteMateria.getIdMateria());

            insertar.executeUpdate();
            System.out.println("Relación estudiante-materia registrada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar relación estudiante-materia: " + e.getMessage());
        }
    }

    public void modificarEstudiante_Tiene_Materia(Estudiante_Tiene_MateriaDTO estudianteMateria) {
        String sqlQuery = "UPDATE Estudiante_Tiene_Materia  SET Materia_id = ? WHERE Estudiante_id = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement actualizar = connection.prepareStatement(sqlQuery)) {
            actualizar.setInt(1, estudianteMateria.getIdMateria());
            actualizar.setInt(2, estudianteMateria.getEstudianteID());
            actualizar.executeUpdate();
            System.out.println("Relación estudiante-materia modificada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar relación estudiante-materia: " + e.getMessage());
        }
    }

    public void eliminarEstudiante_Tiene_Materia(Estudiante_Tiene_MateriaDTO estudianteMateria) {
        String sqlQuery = "DELETE FROM Estudiante_Tiene_Materia  WHERE  Estudiante_Tiene_Materia_id = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement eliminar = connection.prepareStatement(sqlQuery)) {
            eliminar.setInt(1, estudianteMateria.getEstudianteID());
            eliminar.executeUpdate();
            System.out.println("Relación estudiante-materia eliminada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar relación estudiante-materia: " + e.getMessage());
        }
    }

    public List<Estudiante_Tiene_MateriaDTO> obtenerTodosEstudiantesTieneMateria() throws SQLException {
        List<Estudiante_Tiene_MateriaDTO> estudiantesTieneMateria = new ArrayList<>();
        String sqlQuery = "SELECT Estudiante_id, Materia_id FROM Estudiante_Tiene_Materia";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Estudiante_Tiene_MateriaDTO estudianteTieneMateria = new Estudiante_Tiene_MateriaDTO();
                estudianteTieneMateria.setEstudianteID(rs.getInt("Estudiante_id"));
                estudianteTieneMateria.setIdMateria(rs.getInt("Materia_id"));
                estudiantesTieneMateria.add(estudianteTieneMateria);
            }
        }
        return estudiantesTieneMateria;
    }

}




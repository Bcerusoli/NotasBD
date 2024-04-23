package Estudiante_Tiene_Carrera;

import Modelo.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Estudiante_Tiene_CarreraDAO {
    private List<Estudiante_Tiene_CarreraDTO> estudiantesCarreras = new ArrayList<>();
    private Conexion conexion = Conexion.getInstance();


    public Estudiante_Tiene_CarreraDTO findByIdEstudiante_Tiene_CarreraDTO(int estudianteID) throws SQLException {
        String sqlQuery = "SELECT * FROM Estudiante_Tiene_Carrera WHERE Estudiante_id = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, estudianteID);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Estudiante_Tiene_CarreraDTO estudianteCarrera = new Estudiante_Tiene_CarreraDTO();
                    estudianteCarrera.setEstudianteID(rs.getInt("Estudiante_id"));
                    estudianteCarrera.setIdCarrera(rs.getInt("Carrera_idCarrera"));
                    return estudianteCarrera;
                }
            }
        }
        return null;
    }

    public void registrarEstudiante_Tiene_Carrera(Estudiante_Tiene_CarreraDTO estudianteCarrera) {
        String sqlQuery = "INSERT INTO Estudiante_Tiene_Carrera (Estudiante_id, Carrera_idCarrera) VALUES (?, ?)";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement insertar = connection.prepareStatement(sqlQuery)) {
            insertar.setInt(1, estudianteCarrera.getEstudianteID());
            insertar.setInt(2, estudianteCarrera.getIdCarrera());

            insertar.executeUpdate();
            System.out.println("Relación estudiante-carrera registrada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar relación estudiante-carrera: " + e.getMessage());
        }
    }

    public void modificarNota_Tiene_Carrera(Estudiante_Tiene_CarreraDTO estudianteCarrera) {
        String sqlQuery = "UPDATE Estudiante_Tiene_Carrera SET Carrera_idCarrera = ? WHERE Estudiante_id = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement actualizar = connection.prepareStatement(sqlQuery)) {
            actualizar.setInt(1, estudianteCarrera.getIdCarrera());
            actualizar.setInt(2, estudianteCarrera.getEstudianteID());
            actualizar.executeUpdate();
            System.out.println("Relación estudiante-carrera modificada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar relación estudiante-carrera: " + e.getMessage());
        }
    }

    public void eliminarNota_Tiene_Carrera(Estudiante_Tiene_CarreraDTO estudianteCarrera) {
        String sqlQuery = "DELETE FROM Estudiante_Tiene_Carrera WHERE Estudiante_id = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement eliminar = connection.prepareStatement(sqlQuery)) {
            eliminar.setInt(1, estudianteCarrera.getEstudianteID());
            eliminar.executeUpdate();
            System.out.println("Relación estudiante-carrera eliminada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar relación estudiante-carrera: " + e.getMessage());
        }
    }

    public List<Estudiante_Tiene_CarreraDTO> obtenerTodosEstudiantesTieneCarrera() throws SQLException {
        List<Estudiante_Tiene_CarreraDTO> estudianteTieneCarrera = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Estudiante_Tiene_Carrera";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Estudiante_Tiene_CarreraDTO estudianteTieneMateria = new Estudiante_Tiene_CarreraDTO();
                estudianteTieneMateria.setEstudiante_Tiene_Carrera_id(rs.getInt("Estudiante_Tiene_Carrera_id"));
                estudianteTieneMateria.setEstudianteID(rs.getInt("Estudiante_id"));
                estudianteTieneMateria.setIdCarrera(rs.getInt("Carrera_idCarrera"));

                estudianteTieneCarrera.add(estudianteTieneMateria);
            }
        }
        return estudianteTieneCarrera;
    }
}

package Estudiantes;

import Modelo.Conexion;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
       private List<EstudianteDTO> estudiante = new ArrayList<>();

        private Conexion conexion = Conexion.getInstance();

    public EstudianteDTO finByIdEstudianteDTO(int EstudianteID) throws SQLException {
        String sqlQuery = "SELECT * FROM Estudiante WHERE Estudiante_id = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, EstudianteID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                EstudianteDTO estudiante = new EstudianteDTO();
                estudiante.setEstudianteID(rs.getInt("Estudiante_id"));
                estudiante.setNombre(rs.getString("Nombre"));
                estudiante.setFechanacimiento(rs.getDate("Fecha_nacimiento")); // Corregir aquí
                System.out.println("Estudiante_id | Nombre | FechaNacimiento");
                System.out.println(String.format("%-11s | %-9s | %-18s",
                        estudiante.getEstudianteID(), estudiante.getNombre(), estudiante.getFechanacimiento()));
                return estudiante;
            }
            rs.close();
            return null;
        }
    }


    public List<EstudianteDTO> findAll() {

            return estudiante;
        }

    public void registrarEstudiante(EstudianteDTO estudiante) {
        String sqlQuery = "INSERT INTO Estudiante (Nombre, Fecha_nacimiento) VALUES (?, ?)";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement insertar = connection.prepareStatement(sqlQuery)) {
            insertar.setString(1, estudiante.getNombre());
            insertar.setDate(2, (Date) estudiante.getFechanacimiento());
            int filasInsertadas = insertar.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Estudiante registrado exitosamente.");
            } else {
                System.out.println("Error al registrar el estudiante.");
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar el estudiante: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void modificarEstudiante(EstudianteDTO estudiante) {
            String sqlQuery = "UPDATE Estudiante SET Nombre = ?, Fecha_nacimiento = ? WHERE Estudiante_id = ?";
            try (Connection connection = conexion.connectToPostgres();
                 PreparedStatement actualizar = connection.prepareStatement(sqlQuery)) {
                System.out.println("Se actualizó exitosamente");
                actualizar.setString(1, estudiante.getNombre());
                actualizar.setDate(2, (Date) estudiante.getFechanacimiento());
                actualizar.setInt(3, estudiante.getEstudianteID());
                actualizar.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        public void eliminarEstudiante(EstudianteDTO estudiante) {
            String sqlQuery = "DELETE FROM Estudiante WHERE Estudiante_id = ?";
            try (Connection connection = conexion.connectToPostgres();
                 PreparedStatement eliminar = connection.prepareStatement(sqlQuery)) {
                System.out.println("Se eliminó exitosamente");
                eliminar.setInt(1, estudiante.getEstudianteID());
                eliminar.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    public List<EstudianteDTO> obtenerTodosEstudiantes() throws SQLException {
        List<EstudianteDTO> estudiantes = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Estudiante";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                EstudianteDTO estudiante = new EstudianteDTO();
                estudiante.setEstudianteID(rs.getInt("Estudiante_id"));
                estudiante.setNombre(rs.getString("Nombre"));
                estudiante.setFechanacimiento(rs.getDate("Fecha_nacimiento"));
                estudiantes.add(estudiante);
            }
        }
        return estudiantes;
    }

}

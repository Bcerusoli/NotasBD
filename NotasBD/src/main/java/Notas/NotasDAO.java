package Notas;

import Materia.MateriaDTO;
import Modelo.Conexion;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotasDAO {
    private List<NotasDTO> Notas= new ArrayList<>() ;
    private Conexion conexion = Conexion.getInstance();
    public NotasDTO findByIdNotasDTO(int idNota) throws SQLException {
        String sqlQuery = "SELECT * FROM Notas WHERE idNotas = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, idNota);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                NotasDTO nota = new NotasDTO();
                nota.setIdNota(rs.getInt("idNotas"));
                nota.setIdEstudiante(rs.getInt("Estudiante_id"));
                nota.setIdMateria(rs.getInt("Materia_id"));
                nota.setPrimerParcial(rs.getDouble("PrimerParcial"));
                nota.setSegundoParcial(rs.getDouble("SegundoParcial"));
                nota.setFinal_examen(rs.getDouble("Final_examen"));
                return nota;
            }
            rs.close();
            return null;
        }
    }
    public List<NotasDTO> findAll() {
        return Notas;
    }

    public void registrarNota(NotasDTO nota) {
        String sqlQuery = "INSERT INTO Notas (Estudiante_id, Materia_id, PrimerParcial, SegundoParcial, Final_examen) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement insertar = connection.prepareStatement(sqlQuery)) {
            insertar.setInt(1, nota.getIdEstudiante());
            insertar.setInt(2, nota.getIdMateria());
            insertar.setDouble(3, nota.getPrimerParcial());
            insertar.setDouble(4, nota.getSegundoParcial());
            insertar.setDouble(5, nota.getFinal_examen());
            int filasInsertadas = insertar.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Nota registrada exitosamente.");
            } else {
                System.out.println("Error al registrar la nota.");
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar la nota: " + e.getMessage());
            e.printStackTrace(); // Imprimir la traza de la excepción en la consola
        }
    }

    public void modificarNotas(NotasDTO notas) {
        String sqlQuery = "UPDATE Notas SET PrimerParcial = ?, SegundoParcial = ?, Final_examen = ? WHERE idNotas = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement actualizar = connection.prepareStatement(sqlQuery)) {
            actualizar.setDouble(1, notas.getPrimerParcial());
            actualizar.setDouble(2, notas.getSegundoParcial());
            actualizar.setDouble(3, notas.getFinal_examen());
            actualizar.setInt(4, notas.getIdNota());
            actualizar.executeUpdate();
            System.out.println("Nota actualizada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar la nota: " + e.getMessage());
        }
    }

    public void eliminarNotas(NotasDTO notas) {
        String sqlQuery = "DELETE FROM Notas WHERE idNotas = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement eliminar = connection.prepareStatement(sqlQuery)) {
            eliminar.setInt(1, notas.getIdNota());
            eliminar.executeUpdate();
            System.out.println("Nota eliminada exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar la nota: " + e.getMessage());
        }
    }

    public List<NotasDTO> obtenerTodosNotas() {
        List<NotasDTO> notas = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Notas";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                NotasDTO nota = new NotasDTO();
                nota.setIdNota(rs.getInt("idNotas"));
                nota.setIdEstudiante(rs.getInt("Estudiante_id"));
                nota.setIdMateria(rs.getInt("Materia_id"));
                nota.setPrimerParcial(rs.getDouble("PrimerParcial"));
                nota.setSegundoParcial(rs.getDouble("SegundoParcial"));
                nota.setFinal_examen(rs.getDouble("Final_examen"));
                notas.add(nota);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las notas: " + e.getMessage());
        }
        return notas;
    }
    public BigDecimal calcularPromedioNota(int estudianteId, int materiaId) {
        String sqlQuery = "{CALL calcular_promedio_nota(?, ?, ?)}";
        try (Connection connection = conexion.connectToPostgres();
             CallableStatement statement = connection.prepareCall(sqlQuery)) {
            statement.setInt(1, estudianteId);
            statement.setInt(2, materiaId);
            statement.registerOutParameter(3, Types.DECIMAL);
            statement.execute();
            BigDecimal promedioNota = statement.getBigDecimal(3);
            return promedioNota;
        } catch (SQLException e) {
            System.out.println("Error al calcular el promedio de nota: " + e.getMessage());
        }
        return BigDecimal.ZERO;
    }
    public List<NotasDTO> verNotasEstudianteMateria(int estudianteId, int materiaId) {
        List<NotasDTO> notas = new ArrayList<>();
        String sqlQuery = "{CALL ver_notas_estudiante_materia(?, ?)}";
        try (Connection connection = conexion.connectToPostgres();
             CallableStatement statement = connection.prepareCall(sqlQuery)) {
            statement.setInt(1, estudianteId);
            statement.setInt(2, materiaId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                NotasDTO nota = new NotasDTO();
                nota.setPrimerParcial(rs.getDouble("PrimerParcial"));
                nota.setSegundoParcial(rs.getDouble("SegundoParcial"));
                nota.setFinal_examen(rs.getDouble("Final_examen"));
                notas.add(nota);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las notas del estudiante en la materia: " + e.getMessage());
        }
        return notas;
    }
}















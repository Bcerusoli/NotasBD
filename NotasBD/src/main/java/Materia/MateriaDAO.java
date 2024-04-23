package Materia;

import Estudiantes.EstudianteDTO;
import Modelo.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO {
    private List<MateriaDTO> Materia= new ArrayList<>() ;
    private Conexion conexion= Conexion.getInstance();
    public MateriaDTO findByIdMateriaDTO(int idMateria) throws SQLException {
        String sqlQuery = "SELECT * FROM Materia WHERE idMateria = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, idMateria);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                MateriaDTO materia = new MateriaDTO();
                materia.setIdMaterial(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("Nombre"));
                materia.setNumero_creditos(rs.getInt("Numero_creditos"));
                System.out.println("idMateria | Nombre | Numero_creditos");
                System.out.println(String.format("%-11s | %-9s | %-18s",
                        materia.getIdMaterial(), materia.getNombre(), materia.getNumero_creditos()));
                return materia;
            }
            rs.close();
            return null;
        }
    }

    public List<MateriaDTO> findAll() {
        return Materia;
    }
    public void registrarMateria(MateriaDTO materia) {
        String sqlQuery = "INSERT INTO Materia (Nombre, Numero_creditos) VALUES (?, ?)";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement insertar = connection.prepareStatement(sqlQuery)) {
            insertar.setString(1, materia.getNombre());
            insertar.setInt(2, materia.getNumero_creditos());
            int filasInsertadas = insertar.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Materia registrada exitosamente.");
            } else {
                System.out.println("Error al registrar la materia.");
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar la materia: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void modificarMateria(MateriaDTO materia) {
        String sqlQuery = "UPDATE Materia SET Nombre = ?, Numero_creditos = ? WHERE idMateria = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement actualizar = connection.prepareStatement(sqlQuery)) {
            System.out.println("Se actualizó exitosamente");
            actualizar.setString(1, materia.getNombre());
            actualizar.setInt(2, materia.getNumero_creditos());
            actualizar.setInt(3, materia.getIdMaterial());
            actualizar.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar la materia: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void eliminarMateria(MateriaDTO materia) {
        String sqlQuery = "DELETE FROM Materia WHERE idMateria = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement eliminar = connection.prepareStatement(sqlQuery)) {
            System.out.println("Se eliminó exitosamente");
            eliminar.setInt(1, materia.getIdMaterial());
            eliminar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public List<MateriaDTO> obtenerTodosMaterias() throws SQLException {
        List<MateriaDTO> materias = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Materia";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                MateriaDTO materia = new MateriaDTO();
                materia.setIdMaterial(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("Nombre"));
                materia.setNumero_creditos(rs.getInt("Numero_creditos"));
                materias.add(materia);
            }
        }
        return materias;
    }
    public List<MateriaDTO> obtenerMateriasDeEstudiante(int estudianteId) {
        List<MateriaDTO> materias = new ArrayList<>();
        String sqlQuery = "{CALL obtener_materias_de_estudiante(?)}";
        try (Connection connection = conexion.connectToPostgres();
             CallableStatement statement = connection.prepareCall(sqlQuery)) {
            statement.setInt(1, estudianteId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                MateriaDTO materia = new MateriaDTO();
                materia.setIdMaterial(rs.getInt("Materia_id"));
                materia.setNombre(rs.getString("Nombre_materia"));
                materia.setNumero_creditos(rs.getInt("Numero_creditos"));
                materias.add(materia);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las materias del estudiante: " + e.getMessage());
            e.printStackTrace();
        }
        return materias;
    }




}

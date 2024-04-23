package Carrera;

import Modelo.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarreraDAO {
    private List<CarreraDTO> Carrera = new ArrayList<>();
    private Conexion conexion = Conexion.getInstance();
    public CarreraDTO findByIdCarreraDTO(int idCarrera) throws SQLException {
        String sqlQuery = "SELECT * FROM Carrera WHERE idCarrera = ?";

        Connection connection = conexion.connectToPostgres();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, idCarrera);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                CarreraDTO carrera = new CarreraDTO();
                carrera.setIdcarrera(rs.getInt("idCarrera")); // Corregir aquí
                carrera.setNombre(rs.getString("Nombre"));

                System.out.println("idCarrera | Nombre ");
                System.out.println(String.format("%-11s | %-9s ",
                        carrera.getIdcarrera(), carrera.getNombre() ));

                return carrera;
            }
            rs.close();
            return null;
        }
    }

    public void registrarCarrera(CarreraDTO carrera) {
        String sqlQuery = "INSERT INTO Carrera (Nombre) VALUES (?)";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement insertar = connection.prepareStatement(sqlQuery)) {
            System.out.println("Se ingresó exitosamente");
            insertar.setString(1, carrera.getNombre());
            insertar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void modificarCarrera(CarreraDTO carrera) {
        String sqlQuery = "UPDATE Carrera SET Nombre = ? WHERE idCarrera = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement actualizar = connection.prepareStatement(sqlQuery)) {
            System.out.println("Se actualizó exitosamente");
            actualizar.setString(1, carrera.getNombre());
            actualizar.setInt(2, carrera.getIdcarrera());
            actualizar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void eliminarCarrera(CarreraDTO carrera) {
        String sqlQuery = "DELETE FROM Carrera WHERE idCarrera = ?";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement eliminar = connection.prepareStatement(sqlQuery)) {
            System.out.println("Se eliminó exitosamente");
            eliminar.setInt(1, carrera.getIdcarrera());
            eliminar.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public List<CarreraDTO> obtenerTodosCarreras() throws SQLException {
        List<CarreraDTO> carreras = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Carrera";
        try (Connection connection = conexion.connectToPostgres();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CarreraDTO carrera = new CarreraDTO();
                carrera.setIdcarrera(rs.getInt("idCarrera"));
                carrera.setNombre(rs.getString("Nombre"));
                carreras.add(carrera);
            }
        } catch (SQLException e) {
            // Lanzar la excepción nuevamente para que pueda ser manejada por el código que llama a este método
            throw e;
        }
        return carreras;
    }



}

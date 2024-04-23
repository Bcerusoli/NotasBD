package Modelo;

import java.sql.*;

public class Conexion {
    private String host = "127.0.0.1";
    private String port = "5432";
    private String database = "Notas";
    private String user = "postgres";
    private String pass = "root";

    private static Conexion instance;

    private Conexion(){

    }

    public Connection connectToPostgres() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            String connectionString = "jdbc:postgresql://" + host + ":" + port + "/" + database;
            conn = DriverManager.getConnection(connectionString, user, pass);
            System.out.println("Modelo.Conexion Postgres - Java EXITOSA");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void executeSQL(String sql) {
        try (Connection con = connectToPostgres()) {
            Statement consulta = con.createStatement();
            consulta.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getSQL(String sql, int columnIndex) {
        try (Connection con = connectToPostgres()) {
            Statement consulta = con.createStatement();
            ResultSet rs=consulta.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString(columnIndex));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Conexion getInstance() {
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }

}
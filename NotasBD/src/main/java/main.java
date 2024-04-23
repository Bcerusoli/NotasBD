import Modelo.Conexion;

public class main {
    public static void main(String[] args) {
        Conexion con = Conexion.getInstance();
        con.connectToPostgres();

    }
}

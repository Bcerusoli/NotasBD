package Estudiantes;

import java.util.Date;

public class EstudianteDTO {
    int EstudianteID;
    String Nombre;
    Date fechanacimiento;

    public int getEstudianteID() {
        return EstudianteID;
    }

    public void setEstudianteID(int estudianteID) {
        EstudianteID = estudianteID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
}

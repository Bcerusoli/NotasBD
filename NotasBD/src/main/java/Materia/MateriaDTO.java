package Materia;

public class MateriaDTO {
    int idMateria;
    String nombre;
    int Numero_creditos;

    public int getIdMaterial() {
        return idMateria;
    }

    public void setIdMaterial(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero_creditos() {
        return Numero_creditos;
    }

    public void setNumero_creditos(int numero_creditos) {
        Numero_creditos = numero_creditos;
    }
}

package Notas;

public class NotasDTO {
    int idNota;
    int EstudianteID;
    int idMateria;
    Double PrimerParcial;
    Double SegundoParcial;
    Double Final_examen;

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public int getIdEstudiante() {
        return EstudianteID;
    }

    public void setIdEstudiante(int EstudianteID) {
        this.EstudianteID = EstudianteID;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public Double getPrimerParcial() {
        return PrimerParcial;
    }

    public void setPrimerParcial(Double primerParcial) {
        PrimerParcial = primerParcial;
    }

    public Double getSegundoParcial() {
        return SegundoParcial;
    }

    public void setSegundoParcial(Double segundoParcial) {
        SegundoParcial = segundoParcial;
    }

    public Double getFinal_examen() {
        return Final_examen;
    }

    public void setFinal_examen(Double final_examen) {
        Final_examen = final_examen;
    }
}

package edu.escuelaing.ieti.estudiapp.dtos;

public class PlanOperativoDto {

    private String id;

    private String NombreEstudiante;
    private String Materia;
    private String Tematica;
    private String fecha;

    private String razon;
    private String porcentaje;

    public PlanOperativoDto(){}

    public PlanOperativoDto(String id, String estudiante, String materia, String tematica, String Date, String razon, String porcentaje){
        this.id = id;
        this.NombreEstudiante = estudiante;
        this.Materia=materia;
        this.Tematica = tematica;
        this.fecha = Date;
        this.razon = razon;
        this.porcentaje = porcentaje;
    }

    public String getMateria() {
        return Materia;
    }
    public void setMateria(String materia) {
        Materia = materia;
    }
    public String getTematica() {
        return Tematica;
    }
    public void setTematica(String tematica) {
        Tematica = tematica;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getPorcentaje() {
        return porcentaje;
    }
    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreEstudiante() {
        return NombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        NombreEstudiante = nombreEstudiante;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }
}

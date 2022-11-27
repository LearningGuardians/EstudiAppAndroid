package edu.escuelaing.ieti.estudiapp.entities;

import java.time.LocalDate;

import edu.escuelaing.ieti.estudiapp.dtos.PlanOperativoDto;

public class PlanOperativo {
    private String id;
    private String nombreEstudiante;
    private String Materia;
    private String Tematica;
    private String fechaDeCreacion;
    private String razon;
    private String porcentaje;

    private String SalidaColegio;
    private String fechaActividad;

    private String extraCurriculares;
    /**
     * Constructor vacio, en caso que se quiera crear con los datos basicos
     * entonces se le deja un identificador y la fecha de creacion
     */
    public PlanOperativo(){
        this.id = String.valueOf(Math.floor(Math.random()*10+1));
        this.fechaDeCreacion = LocalDate.now().toString();
    }

    /**
     * Constructor generado para la creacion de planes operativos, donde el usuario da todos los parametros
     */
    public PlanOperativo(PlanOperativoDto operativoDto){
        this.id = operativoDto.getId();
        this.nombreEstudiante = operativoDto.getNombreEstudiante();
        this.Materia=operativoDto.getMateria();
        this.Tematica = operativoDto.getTematica();
        this.fechaDeCreacion = operativoDto.getFecha();
        this.razon = operativoDto.getRazon();
        this.porcentaje = operativoDto.getPorcentaje();
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
        return fechaDeCreacion;
    }
    public void setFecha(String fecha) {
        this.fechaDeCreacion = fecha;
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
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(String fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getSalidaColegio() {
        return SalidaColegio;
    }

    public void setSalidaColegio(String salidaColegio) {
        SalidaColegio = salidaColegio;
    }

    public String getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(String fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public String getExtraCurriculares() {
        return extraCurriculares;
    }

    public void setExtraCurriculares(String extraCurriculares) {
        this.extraCurriculares = extraCurriculares;
    }
}


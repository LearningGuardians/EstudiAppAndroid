package edu.escuelaing.ieti.estudiapp.entities;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

import edu.escuelaing.ieti.estudiapp.dtos.PlanOperativoDto;

public class PlanOperativo {
    private String id;
    private String Materia;
    private String horaLlegada;
    private String nombrePlan;
    private String horaComienzo;
    private String razon;
    private String fechaDeCreacion;
    
    /**
     * Constructor vacio, en caso que se quiera crear con los datos basicos
     * entonces se le deja un identificador y la fecha de creacion
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public PlanOperativo(){
        this.id = String.valueOf(Math.floor(Math.random()*10+1));
        this.fechaDeCreacion = LocalDate.now().toString();
    }

    /**
     * Constructor donde el usuario da todos los parametros del plan operativo
     * @param materia
     * @param hora1
     * @param nombrePlan
     * @param hora2
     * @param razon
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public PlanOperativo(String materia, String hora1, String nombrePlan, String hora2, String razon){
        this.id = String.valueOf(Math.floor(Math.random()*10+1));
        this.Materia = materia;
        this.horaLlegada = hora1;
        this.nombrePlan = nombrePlan;
        this.horaComienzo = hora2;
        this.razon = razon;
        this.fechaDeCreacion = LocalDate.now().toString();
    }

    /**
     * Constructor de plan operativo donde no se necesita de una hora de llegada al colegio.
     * @param materia
     * @param hora1
     * @param nombrePlan
     * @param razon
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public PlanOperativo(String materia, String hora1, String nombrePlan, String razon){
        this.id = String.valueOf(Math.floor(Math.random()*10+1));
        this.Materia = materia;
        this.nombrePlan = nombrePlan;
        this.horaComienzo = hora1;
        this.razon = razon;
        this.fechaDeCreacion = LocalDate.now().toString();
    }

    /**
     * Constructor generado para la creacion de planes operativos, donde el usuario da todos los parametros
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public PlanOperativo(PlanOperativoDto operativoDto){
        this.id = operativoDto.getId();
        this.Materia=operativoDto.getMateria();
        this.fechaDeCreacion = operativoDto.getFecha();
        this.razon = operativoDto.getRazon();
    }

    public String getMateria() {
        return Materia;
    }
    public void setMateria(String materia) {
        Materia = materia;
    }
    public String getFecha() {
        return fechaDeCreacion;
    }
    public void setFecha(String fecha) {
        this.fechaDeCreacion = fecha;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public String getHoraComienzo() {
        return horaComienzo;
    }

    public void setHoraComienzo(String horaComienzo) {
        this.horaComienzo = horaComienzo;
    }
}


package edu.escuelaing.ieti.estudiapp.ia;

import android.os.Build;
import android.util.Pair;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;

import edu.escuelaing.ieti.estudiapp.entities.PlanOperativo;

public class LearningIA {

    private LocalTime LlegadaCasa = null;

    private ConcurrentHashMap<String, Pair> extraCurriculares = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, Pair> particionEstudio = new ConcurrentHashMap<>();

    private final PlanOperativo p_operativo;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LearningIA(PlanOperativo planOperativo) {
        this.p_operativo = planOperativo;
    }

    /**
     * Ejecuta la IA para que empiece a revisar a partir de los datos del JSON que estudio
     * es optimo para el estudiante
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void start() {
        reparticionEstudio();
    }

    /**
     * Funcion generada para retornar el ConcurrentHashMap con la particion de horas del dia, esto de
     * acuerdo con que tipo de estudio se le escogio al estudiante
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void reparticionEstudio() {
        System.out.println("ENTRA A REPARTICION");
        switch ("") {
            case "Refuerzo":
                this.particionEstudio = particion(15, 40, LocalTime.of(8, 0));
                break;
            case "Quiz":
                this.particionEstudio = particion(20, 30, LocalTime.of(9, 30));
                break;
            case "Evaluacion":
                this.particionEstudio = particion(35, 20, LocalTime.of(11, 20));
                break;

        }
    }

    /**
     * Funcion generada para generar la particion de estudio de acuerdo con la hora de inicio del estudiante,
     * el descanso que se le da y la hora maxima de estudio
     *
     * CAMBIO -> QUE PASA SI EL ESTUDIANTE SE QUIERE PASAR DE HORA Y SEGUIR ESTUDIANDO ??
     *
     * @param tiempoEstudio
     * @param descanso
     * @param horaMaxima
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ConcurrentHashMap particion(int tiempoEstudio, int descanso, LocalTime horaMaxima) {
        ConcurrentHashMap<String, Pair> particionFinal = new ConcurrentHashMap<>();
        LocalTime horaInicio = this.LlegadaCasa;
        LocalTime horaFin = null;
        int var = 0;
        while (!horaInicio.equals(horaMaxima)) {
            //Insercion de particion en hashmap
            var += 1;
            //Suma a la hora de fin los minutos de estudio
            horaFin = horaInicio.plusMinutes(tiempoEstudio);
            particionFinal.put("Estudio_" + var, new Pair<>(horaInicio, horaFin));
            // se deja la nueva hora de inicio como la de fin mas un descanso, segun lo establecido por IA
            horaInicio = horaInicio.plusMinutes(descanso);
        }
        return particionFinal;
    }

    /**
     * Getter llegada casa
     *
     * @return
     */
    public LocalTime getLlegadaCasa() {
        return LlegadaCasa;
    }

    /**
     * Setter llegada casa
     *
     * @param llegadaCasa
     */
    public void setLlegadaCasa(LocalTime llegadaCasa) {
        LlegadaCasa = llegadaCasa;
    }

    /**
     * Getter Extracurriculares
     *
     * @return
     */
    public ConcurrentHashMap<String, Pair> getExtraCurriculares() {
        return extraCurriculares;
    }

    /**
     * Setter Extracurriculares
     */
    public void setExtraCurriculares(ConcurrentHashMap<String, Pair> extraCurriculares) {
        this.extraCurriculares = extraCurriculares;
    }

    /**
     * Getter Particion Estudio
     *
     * @return
     */
    public ConcurrentHashMap getParticionEstudio() {
        return particionEstudio;
    }

    /**
     * Setter Particion Estudio
     * @param particionEstudio
     */
    public void setParticionEstudio(ConcurrentHashMap particionEstudio) {
        this.particionEstudio = particionEstudio;
    }
}


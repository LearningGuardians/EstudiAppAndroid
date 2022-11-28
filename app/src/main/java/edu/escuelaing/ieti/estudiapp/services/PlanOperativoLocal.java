package edu.escuelaing.ieti.estudiapp.services;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import edu.escuelaing.ieti.estudiapp.dtos.PlanOperativoDto;
import edu.escuelaing.ieti.estudiapp.entities.PlanOperativo;

public class PlanOperativoLocal {

    public static ConcurrentHashMap<String,PlanOperativo> planesOperativos = new ConcurrentHashMap<>();


    public static Collection<PlanOperativo> getAll(){
        return planesOperativos.values();
    }

    public static PlanOperativo getById(String id){
        return planesOperativos.get(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static PlanOperativo create(PlanOperativo pOperativo){
        System.out.println("Creando Plan Operativo_______________-");
        String id = pOperativo.getId();
        planesOperativos.put(id,pOperativo);
        System.out.println("Plan operativo Creado________" + pOperativo.getNombrePlan());
        return getById(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static PlanOperativo update(PlanOperativo operativo, String id){
        System.out.println("Actualizando Plan Operativo_______________-");
        delete(id);
        planesOperativos.put(id,operativo);
        System.out.println("Plan operativo Actualizado________" + operativo.getNombrePlan());
        return planesOperativos.get(id);
    }

    public static boolean  delete(String id){
        planesOperativos.remove(id);
        return planesOperativos.get(id).equals(null);
    }
}
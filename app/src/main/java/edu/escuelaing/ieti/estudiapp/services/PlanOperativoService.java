package edu.escuelaing.ieti.estudiapp.services;

import java.util.List;

import edu.escuelaing.ieti.estudiapp.PlanOperativo;
import edu.escuelaing.ieti.estudiapp.dtos.PlanOperativoDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlanOperativoService {
    @GET("operativo")
    Call<List<PlanOperativoDto>> getAll();

    @GET("operativo/{id}")
    Call<PlanOperativoDto> getPlanOperativo(@Path("id") String id);

    @POST("operativo")
    Call<PlanOperativoDto> createPOperativo(@Body PlanOperativoDto pOperativoDTO);

    @PUT("operativo/{id}")
    Call<PlanOperativo> updatePOperativo(@Path("id") String id, @Body PlanOperativoDto pOperativoDTO);

    @DELETE("operativo/id")
    Call<Boolean> delete(@Path("id") String id);

}
